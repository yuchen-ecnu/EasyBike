import datetime
import os
import os.path
import random
import sqlite3
import threading
import time
import ujson
from concurrent.futures import ThreadPoolExecutor

import numpy as np
import requests
from retrying import retry

from modules.ProxyProvider import ProxyProvider
from requests.packages.urllib3.exceptions import InsecureRequestWarning

class Crawler:
    def __init__(self):
        self.start_time = datetime.datetime.now()
        self.csv_path = "./db/" + datetime.datetime.now().strftime("%Y%m%d")
        os.makedirs(self.csv_path, exist_ok=True)
        self.csv_name = self.csv_path + "/" + datetime.datetime.now().strftime("%Y%m%d-%H%M%S") + '.csv'
        self.db_name = "./temp.db"
        self.lock = threading.Lock()
        self.proxyProvider = ProxyProvider()
        self.total = 0
        self.done = 0

    def get_nearby_bikes(self, args):
        try:
            url = "https://mwx.mobike.com/mobike-api/rent/nearbyBikesInfo.do"

            payload = "latitude=%s&longitude=%s&errMsg=getMapCenterLocation" % (args[0], args[1])

            headers = {
                'charset': "utf-8",
                'platform': "4",
                "referer":"https://servicewechat.com/wx40f112341ae33edb/1/",
                'content-type': "application/x-www-form-urlencoded",
                'user-agent': "MicroMessenger/6.5.4.1000 NetType/WIFI Language/zh_CN",
                'host': "mwx.mobike.com",
                'connection': "Keep-Alive",
                'accept-encoding': "gzip",
                'cache-control': "no-cache"
            }

            self.request(headers, payload, args, url)
        except Exception as ex:
            print(ex)

    def request(self, headers, payload, args, url):
        while True:
            proxy = self.proxyProvider.pick()
            try:
                response = requests.request(
                    "POST", url, data=payload, headers=headers,
                    proxies={"https": proxy.url},
                    timeout=5,verify=False
                )

                with self.lock:
                    with sqlite3.connect(self.db_name) as c:
                        try:
                            # print(response.text)
                            decoded = ujson.decode(response.text)['object']
                            self.done += 1
                            for x in decoded:
                                c.execute("INSERT INTO mobike VALUES (%d,'%s',%d,%d,%s,%s,%s,%s)" % (
                                    int(time.time()) * 1000, x['bikeIds'], int(x['biketype']), int(x['distId']),
                                    x['distNum'], x['type'], x['distX'],
                                    x['distY']))

                            timespend = datetime.datetime.now() - self.start_time
                            percent = self.done / self.total
                            total = timespend / percent
                            #print(args, self.done, percent * 100, self.done / timespend.total_seconds() * 60, total,
                            #     total - timespend)
                        except Exception as ex:
                            print(ex)
                    break
            except Exception as ex:
                proxy.fatal_error()

    def start(self):
        left = 31.271843
        top = 121.376084
        right = 31.20073
        bottom = 121.49805

        offset = 0.002

        if os.path.isfile(self.db_name):
            os.remove(self.db_name)

        try:
            with sqlite3.connect(self.db_name) as c:
                c.execute('''CREATE TABLE mobike
                    (Time DATETIME, bikeIds VARCHAR(12), bikeType TINYINT,distId INTEGER,distNum TINYINT, type TINYINT, x DOUBLE, y DOUBLE)''')
        except Exception as ex:
            pass

        executor = ThreadPoolExecutor(max_workers=250)
        print("Start")
        self.total = 0
        lat_range = np.arange(left, right, -offset)
        for lat in lat_range:
            lon_range = np.arange(top, bottom, offset)
            for lon in lon_range:
                self.total += 1
                executor.submit(self.get_nearby_bikes, (lat, lon))

        executor.shutdown()
        self.group_data()
        os.system("python importToDb.py")

    def group_data(self):
        print("Creating group data")
        conn = sqlite3.connect(self.db_name)
        cursor = conn.cursor()

        f = open(self.csv_name, "w")
        for row in cursor.execute('''SELECT * FROM mobike'''):
            timestamp, bikeIds, bikeType, distId, distNumber, type, lon, lat = row
            f.write("%s,%s,%s,%s,%s,%s,%s,%s\n" % (
                datetime.datetime.fromtimestamp(int(timestamp) / 1000).isoformat(), bikeIds, bikeType, distId, distNumber, type, lon, lat))
        f.flush()
        f.close()

        os.system("gzip -9 " + self.csv_name)

# 禁用安全请求警告
requests.packages.urllib3.disable_warnings(InsecureRequestWarning)
Crawler().start()
