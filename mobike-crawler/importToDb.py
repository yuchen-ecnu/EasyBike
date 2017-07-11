# -*- coding: utf-8 -*- 
import glob
import os
import pickle
import datetime
import time
import csv
import pandas as pd
import psycopg2
import pytz
import concurrent.futures
import pymysql
import string 

base_path = "./db/"+datetime.datetime.now().strftime("%Y%m%d")+"/"
csv_files = glob.glob("db/"+datetime.datetime.now().strftime("%Y%m%d")+"/*.csv")
csv_files.sort()
SAVE_FILE = "progress.bin"
jobs = []
first_time = True

last_imported_file = None
resume = True

for csv_file in csv_files:
    if not resume:
        if csv_file == last_imported_file:
            resume = True

        continue

    if first_time:
        first_time = False
        try:
            print("Drop index")
            cnx = pymysql.connect(host='localhost',port = 3306,user='root',passwd='1234',db ='crawel')
            with cnx.cursor() as c:
                c.execute("DROP INDEX mobike_id_index on mobike;")
                c.execute("DROP INDEX mobike_index on mobike;")
        except Exception as ex:
            print(ex)
            cnx.commit()
            pass
        print("Done drop index")

    jobs.append(csv_file)

def run(csv_file):
        temp_file = "D:\\"+os.path.basename(csv_file)
        tz = pytz.timezone("Asia/Shanghai")
        print("Start Job :"+temp_file)
        try:
            cnx = pymysql.connect(host='localhost',port = 3306,user='root',passwd='1234',db ='crawel')
            df = pd.read_csv(csv_file, index_col=0,
                             names=["bikeid", "biketype", 'distid', 'distnum', 'type', "lon", "lat"],
                             parse_dates=True)
            df.drop_duplicates(subset=['distid', 'lon', 'lat'], inplace=True)
            df = df[["biketype", 'distid', "lon", "lat"]]
            df.tz_localize(tz)
            cursor = cnx.cursor()
            df.to_csv(temp_file, header=False, date_format="%Y-%m-%d %H:%M:%S")
            out=open(temp_file);
            #first time to insert
            cursor.execute("select count(1) from mobike")
            result = cursor.fetchall()
            if result[0][0]==0:
                print("当前数据库无数据！")

            #compare data
            else:
                df = csv.reader(out)
                #csv iterate
                for row in df:
                    cursor.execute("select count(1) from mobike where distId = %s",row[2])
                    origin = cursor.fetchall() 
                    #bike stop a trip
                    if origin[0][0]==0:
                        cursor.execute("select count(1) from test where bikeid = %s and endtime is NULL",row[2])
                        trip = cursor.fetchall()
                        if trip[0][0]!=0:
                            cursor.execute("select count(1) from test where bikeid = %s and endtime is NULL and startlon=%s and startlat = %s",(row[2],row[3],row[4]))
                            tmp = cursor.fetchall()
                            if tmp[0][0]!=0:
                                cursor.execute("delete from test where bikeid = %s and endtime is NULL",row[2])
                            else:
                                print("Got a Trip")
                                cursor.execute("update test set endtime=%s, endlon=%s,endlat=%s where bikeid = %s and endtime is NULL",(row[0],row[3],row[4],row[2]))
                        else:
                            continue
                    else:
                        cursor.execute("delete from mobike where distId = %s",row[2])
                #db iterate
                cursor.execute("select * from mobike")
                res = cursor.fetchall()
                
                for row2 in res:
                    #bike is riding
                    cursor.execute("select count(1) from test where bikeid = %s and endtime is NULL and startlon=%s and startlat = %s",(row2[2],row2[3],row2[4]))
                    temp = cursor.fetchone()
                    if temp[0]!=0:
                            cursor.execute("delete from test where bikeid = %s and endtime is NULL",row2[2])
                    else:
                        cursor.execute("insert into test values (null,%s,%s,null,%s,%s,null,null)",(row2[2],row2[0],row2[3],row2[4]))

                cursor.execute("delete from mobike")
                    
            cursor.execute("load data infile '%s' into table mobike fields terminated by ','  optionally enclosed by '\"' escaped by '\"' lines terminated by '\\r\\n';" % (temp_file))

        except Exception as ex:
            print(ex)
        finally:
            out.close()
            cnx.commit()
            last_imported_file = csv_file
            os.remove(temp_file)
            pickle.dump(last_imported_file, open(SAVE_FILE, "wb"))
            os.remove(SAVE_FILE)
            

print("task begin")
if(len(jobs)>0):
    #for job in jobs:
        run(jobs[len(jobs)-1])
print("task end")
if not first_time:
        cnx = pymysql.connect(host='localhost',port = 3306,user='root',passwd='1234',db ='crawel')
        with cnx.cursor() as c:
            print("Creating index")
            c.execute("CREATE INDEX mobike_id_index ON mobike (distid);")
            c.execute("CREATE INDEX mobike_index ON mobike (time);")
            cnx.commit()

print("Done")

