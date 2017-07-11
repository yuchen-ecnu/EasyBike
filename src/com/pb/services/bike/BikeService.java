package com.pb.services.bike;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.springframework.stereotype.Service;

import com.pb.entity.Bike;
import com.pb.entity.Mobike;
import com.pb.entity.Position;
import com.pb.entity.Test;
import com.pb.entity.Trip;
import com.pb.json.BikeInfo;
import com.pb.json.BikeUnlock;
import com.pb.json.MobikeDistanceJson;
import com.pb.json.MobikeTraceCountJson;
import com.pb.json.MobikeTypeJson;
import com.pb.json.UserInfoForWeb;
import com.pb.services.common.CommService;

@Service(value = "BikeService")
public class BikeService extends CommService {

	/**
	 * 获取所有单车位置信息
	 * 
	 * @return 所有单车的位置信息List
	 */
	public List<BikeInfo> getBikeInfo() {
		List<BikeInfo> result = baseDAO.findBySQLForVO(
				"SELECT city_id as cityId,position_id as positionId,bike_id as bikeId,statues as statues,type as type,password as password,latitude as latitude,longitude as longitude,altitude as altitude,province as province,city as city FROM bike NATURAL JOIN position NATURAL JOIN city WHERE statues = 'lock';",
				BikeInfo.class, null);
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setPassword("");
		}
		return result;
	}
	
	/**
	 * 分页查询单车列表
	 * 
	 * @return
	 */
	public List<BikeInfo> getBikeByPage(String PNO, String PSIZE) {
		int page = Integer.parseInt(PNO);
		int size = Integer.parseInt(PSIZE);
		int start = (page - 1) * size;
		List<BikeInfo> result = baseDAO.findBySQLForVO(
				"SELECT city_id as cityId,position_id as positionId,bike_id as bikeId,statues as statues,type as type,password as password,latitude as latitude,longitude as longitude,altitude as altitude,province as province,city as city FROM bike NATURAL JOIN position NATURAL JOIN city WHERE statues = 'lock' limit ?,?; ",
				BikeInfo.class, new Object[] { start, size });
		return result;
	}
	
	
	/**
	 * 获取所有mobike单车位置信息
	 * 
	 * @return 所有单车的位置信息List
	 */
	public List<MobikeTraceCountJson> getMobike() {
		List<MobikeTraceCountJson> result = baseDAO.findBySQLForVO(
				"SELECT Time as time,biketype as biketype,distid as distid,lon as lon,lat as lat,"
						+ "mobikeid as mobikeid,COUNT(test.bikeid) as tripcount " + "FROM mobike LEFT JOIN test "
						+ "ON mobike.distid = test.bikeid " + "GROUP BY mobike.distid " + "ORDER BY tripcount DESC;",
				MobikeTraceCountJson.class, new Object[] {});
		return result;
	}
	
	public List<MobikeTraceCountJson> getMobikeByPage(String PNO,String PSIZE) {
		int page = Integer.parseInt(PNO);
		int size = Integer.parseInt(PSIZE);
		int start = (page-1)*size;
		List<MobikeTraceCountJson> result = baseDAO.findBySQLForVO(
				"SELECT Time as time,biketype as biketype,distid as distid,lon as lon,lat as lat,"
						+ "mobikeid as mobikeid,COUNT(test.bikeid) as tripcount " + "FROM mobike LEFT JOIN test "
						+ "ON mobike.distid = test.bikeid " + "GROUP BY mobike.distid " + "ORDER BY tripcount DESC limit ?,?;",
				MobikeTraceCountJson.class, new Object[] {start, size});
		return result;
	}

	/**
	 * 根据Id获取mobike
	 * 
	 * @return 所有单车的信息List
	 */
	public List<Mobike> getmobikeById(int id) {
		List<Mobike> result = baseDAO.findByHQL("from Mobike m where m.distid = ? ", new Object[] { id + "" });
		return result;
	}

	/**
	 * 解锁单车
	 * 
	 * @param bikeId
	 * @return 单车处于锁定状态时，返回密码； 否则返回错误码“0001”
	 */
	public String unlockBike(String bikeId) {
		List<BikeUnlock> list = baseDAO.findBySQLForVO(
				"SELECT bike_id as bikeId,statues as statues,password as password FROM bike WHERE bike_id = ? ;",
				BikeUnlock.class, new Object[] { bikeId });
		if (list.size() == 0)
			return "0001";
		else {
			if (list.get(0).getStatues().equals("lock")) { // 单车处于锁定状态，重置状态为riding并返回密码；
				baseDAO.executeSQL("UPDATE bike SET statues = 'riding' WHERE bike_id = ?;", new Object[] { bikeId });
				return list.get(0).getPassword();
			} else
				return "0001"; // 车辆为处于锁定状态，返回错误码
		}
	}

	/**
	 * 骑行结束，锁定单车，更新单车最后位置，并设置Trip结束时间，及相关参数
	 * 
	 * @param bikeId
	 * @return 单车处于骑行状态，并锁定成功时，返回正确码“0000”； 否则返回错误码“0001”
	 */
	public String lockBike(String bikeId, String tripid) {
		List<BikeUnlock> list = baseDAO.findBySQLForVO(
				"SELECT bike_id as bikeId,statues as statues,password as password FROM bike WHERE bike_id = ? ;",
				BikeUnlock.class, new Object[] { bikeId });
		if (list.size() == 0)
			return "0001";
		else {
			if (list.get(0).getStatues().equals("riding")) { // 单车处于骑行状态，重置状态为lock并返回成功码；
				baseDAO.executeSQL("CALL lockBike(?,?);", new Object[] { bikeId, Integer.parseInt(tripid) });
				return "0000";
			} else
				return "0001"; // 车辆为处于非骑行状态，返回错误码
		}
	}

	/**
	 * 修改单车密码
	 * 
	 * @param bikeId
	 * @return 修改成功时，返回正确码“0000”； 密码长度超限时，返回错误码“0001”；
	 */
	public String modifyBikePassword(String bikeId, String password) {
		if (password.length() > 8)
			return "0001";
		baseDAO.executeSQL("UPDATE bike SET password = ? WHERE bike_id = ?;", new Object[] { password, bikeId });
		return "0000"; // 对车辆修改密码操作成功，返回正确码
	}

	public String batchAddition(String bikeID, String latitude, String longitude, String amount, String type) {
		int Amount = Integer.parseInt(amount);
		String password = "000000";
		List<Position> temp = baseDAO.findByHQL(
				"from Position position where position.latitude = ? and position.longitude=?",
				new Object[] { latitude, longitude });
		if (temp.size() == 0) {
			List<Position> po = baseDAO.findBySQLForVO("select max(position_id) as positionId from position;",
					Position.class, null);
			if (po.size() == 0)
				return "0001";
			int positionid = po.get(0).getPositionId()+1;
			baseDAO.executeSQL("INSERT into position VALUES (?,?,?,NULL,5,NULL);",
					new Object[] { positionid, latitude, longitude });

			for (int i = 0; i < Amount; i++) {
				baseDAO.executeSQL("INSERT into bike VALUES (?,?,'lock',?,?);",
						new Object[] { bikeID + i, positionid, type, password });
			}
		} else {
			Position p = temp.get(0);
			int positionID = p.getPositionId();

			for (int i = 0; i < Amount; i++) {
				baseDAO.executeSQL("INSERT into bike VALUES (?,?,'lock',?,?);",
						new Object[] { bikeID + i, positionID, type, password });
			}
		}
		return "0000";
	}

	public String editBike(String bikeId, String statu, String Latitude, String Longitude, String type) {
		int bikeid = Integer.parseInt(bikeId);
		List<Position> temp = baseDAO.findByHQL(
				"from Position position where position.latitude = ? and position.longitude=?",
				new Object[] { Latitude, Longitude });
		if (temp.size() == 0) {
			List<Position> po = baseDAO.findBySQLForVO("select max(position_id) as positionId from position;",
					Position.class, null);
			if (po.size() == 0)
				return "0001";
			int positionid = po.get(0).getPositionId()+1;

			baseDAO.executeSQL("INSERT into position VALUES (?,?,?,NULL,5);",
					new Object[] { positionid, Latitude, Longitude });
			baseDAO.executeSQL("UPDATE bike SET position_id=? , statues=?, type=? WHERE bike_id = ?",
					new Object[] { positionid, statu, type, bikeid });
		} else {
			Position p = temp.get(0);
			int positionID = p.getPositionId();
			baseDAO.executeSQL("UPDATE bike SET position_id=? , statues=?, type=? WHERE bike_id = ?",
					new Object[] { positionID, statu, type, bikeid });
		}
		return "0000";
	}

	public String deleteBike(String bikeid) {
		List<Trip> triptmp = baseDAO.findByHQL("from Trip t where t.bike.bikeId=?",new Object[]{bikeid});
		if(triptmp.size()!=0)return "0001";
		baseDAO.executeSQL("DELETE from bike WHERE bike_id=?", new Object[] { bikeid });
		return "0000";
	}

	/**
	 * 根据mobikeId获取该摩拜单车的trip数据
	 * 
	 * @param bikeId
	 * @return
	 */
	public List<Test> getMobikeTripById(String bikeId) {
		List<Test> result = baseDAO.findByHQL("from Test t where t.bikeid = ?", new Object[] { bikeId });
		return result;
	}

	/**
	 * 根据tripid获取该摩拜单车的trip数据
	 * 
	 * @param bikeId
	 * @return
	 */
	public List<Test> getMobikeTripByTripId(int bikeId) {
		List<Test> result = baseDAO.findByHQL("from Test t where t.id = ?", new Object[] { bikeId });
		return result;
	}

	/**
	 * 获取mobike单车类型数量统计
	 * 
	 * @param
	 * @return
	 */
	public List<MobikeTypeJson> getMobikeTypeNumber() {
		List<MobikeTypeJson> result = baseDAO.findBySQLForVO(
				"SELECT biketype as mobikeType,COUNT(biketype) as count " + "FROM mobike GROUP BY biketype;",
				MobikeTypeJson.class, new Object[] {});
		return result;
	}

	/**
	 * 获取mobike骑行次数
	 * 
	 * @return
	 */
	public List<MobikeTypeJson> getMobikeRideTimes() {
		List<MobikeTypeJson> result = baseDAO
				.findBySQLForVO("SELECT tripcount as mobikeType,count(tripcount) as count FROM "
						+ "(SELECT distid,COUNT(test.bikeid) as tripcount FROM mobike LEFT JOIN test ON mobike.distid = test.bikeid GROUP BY mobike.distid ORDER BY tripcount) as T "
						+ "GROUP BY tripcount;", MobikeTypeJson.class, new Object[] {});
		return result;
	}

	/**
	 * 获取mobike骑行次数
	 * 
	 * @return
	 */
	public List<MobikeDistanceJson> getAllMobikeTrip() {
		List<MobikeDistanceJson> result = new ArrayList();
		List<Trip> trips = baseDAO.findBySQLForVO("select total_length as totalLength from trip", Trip.class, null);
		MobikeDistanceJson t1 = new MobikeDistanceJson("100-1000米", 0);
		MobikeDistanceJson t2 = new MobikeDistanceJson("1000-2000米", 0);
		MobikeDistanceJson t3 = new MobikeDistanceJson("2000-3000米", 0);
		MobikeDistanceJson t4 = new MobikeDistanceJson("3000-4000米", 0);
		MobikeDistanceJson t5 = new MobikeDistanceJson(">4000米", 0);
		for (int i = 0; i < trips.size(); i++) {
			Double length = trips.get(0).getTotalLength();
			if (length >= 0.01 && length < 1) {
				t1.setCount(t1.getCount() + 1);
			} else if (length >= 1 && length < 2) {
				t2.setCount(t2.getCount() + 1);
			} else if (length >= 2 && length < 3) {
				t3.setCount(t3.getCount() + 1);
			} else if (length >= 3 && length < 4) {
				t4.setCount(t4.getCount() + 1);
			} else if (length >= 4) {
				t5.setCount(t5.getCount() + 1);
			}
		}
		result.add(t1);
		result.add(t2);
		result.add(t3);
		result.add(t4);
		result.add(t5);
		return result;
	}
}
