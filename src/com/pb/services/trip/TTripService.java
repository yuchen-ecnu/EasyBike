package com.pb.services.trip;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Position;
import com.pb.entity.Trip;
import com.pb.json.Point;
import com.pb.json.TripInfoForWeb;
import com.pb.services.common.CommService;

@Service(value = "TTripService")
public class TTripService extends CommService {

	/**
	 * 为用户创建行程，同时返回tripid
	 * @param userId
	 * @param bikeId
	 * @return
	 */
	public String buildTrip(String userId, String bikeId) {
		List<Trip> t = baseDAO.findByHQL("from Trip t");
		int tripid = 0;
		if(t.size()!=0) tripid = t.get(0).getTripId()+1;
		baseDAO.executeSQL(
				"INSERT INTO Trip (trip_id,user_id,bike_id,start_time) VALUES (? , ? , ? , NOW());",
				new Object[] { tripid, Integer.parseInt(userId), bikeId });
		return tripid+"";
	}

	public boolean insertPoint(String latitude,String longitude,String tripid,String altitude,String province,String city){
		int result = baseDAO.executeSQL("CALL insertPoint (?,?,?,?,?,?,0)", new Object[]{latitude,longitude,altitude,tripid,province,city});
		return true;
	}
	
	public List<Point> getTraceByTripid(String tripid){
		List<Point> result = baseDAO.findBySQLForVO("select trip_id as tripId,position_id as positionId,"
				+ "latitude as latitude,longitude as longitude,altitude as altitude,"
				+ " province as province,city as city where trip_id = ?"
				+ " from position natural JOIN city",Point.class,new Object[]{Integer.parseInt(tripid)});
		return result;
	}
	
	public List<TripInfoForWeb> getTraceByUserid(String userid){
		List<TripInfoForWeb> result = baseDAO.findBySQLForVO("SELECT user_id AS userId,trip_id AS tripId,type AS type,name AS name,bike_id AS bikeId,start_time AS startTime,end_time AS endTime,avg_speed AS avgSpeed,"+
															"total_length AS totalLength,uphill_times AS uphillTimes,downhill_times AS downhillTimes,calories AS calories "+
															"FROM (SELECT user_id,name FROM user WHERE user_id = ?) AS U NATURAL JOIN (SELECT bike_id,type FROM bike) AS B "+
															"NATURAL JOIN (SELECT * FROM trip WHERE user_id = ?) AS T WHERE T.user_id = U.user_id AND T.bike_id = B.bike_id AND T.user_id = ?;", 
															TripInfoForWeb.class, new Object[]{Integer.parseInt(userid),Integer.parseInt(userid),Integer.parseInt(userid)});
		return result;
	}
	
	public List<Position> getEndPoints(){
		List<Position> result = baseDAO.findBySQLForVO("select position_id as positionId,"
				+ "latitude as latitude,longitude as longitude from position where position_id in"
				+ "(select endPoint from trip)", Position.class, null);
		return result;
	}
	
	public List<Position> getStartPoints(){
		List<Position> result = baseDAO.findBySQLForVO("select position_id as positionId,"
				+ "latitude as latitude,longitude as longitude from position where position_id in"
				+ "(select startPoint from trip)", Position.class, null);
		return result;
	}
}
