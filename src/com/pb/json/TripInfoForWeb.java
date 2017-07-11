package com.pb.json;

import java.sql.Timestamp;

import com.pb.entity.Bike;
import com.pb.entity.User;

public class TripInfoForWeb {
	private Integer tripId;
	private Integer userId;
	private String name;
	private String bikeId;
	private String type;
	private Timestamp startTime;
	private Timestamp endTime;
	private Double avgSpeed;
	private Double totalLength;
	private Integer uphillTimes;
	private Integer downhillTimes;
	private Integer calories;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Double getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(Double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public Double getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Double totalLength) {
		this.totalLength = totalLength;
	}

	public Integer getUphillTimes() {
		return uphillTimes;
	}

	public void setUphillTimes(Integer uphillTimes) {
		this.uphillTimes = uphillTimes;
	}

	public Integer getDownhillTimes() {
		return downhillTimes;
	}

	public void setDownhillTimes(Integer downhillTimes) {
		this.downhillTimes = downhillTimes;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public TripInfoForWeb() {
		super();
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public TripInfoForWeb(Integer tripId, Integer userId, String name, String bikeId, String type, Timestamp startTime,
			Timestamp endTime, Double avgSpeed, Double totalLength, Integer uphillTimes, Integer downhillTimes,
			Integer calories) {
		super();
		this.tripId = tripId;
		this.userId = userId;
		this.name = name;
		this.bikeId = bikeId;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.avgSpeed = avgSpeed;
		this.totalLength = totalLength;
		this.uphillTimes = uphillTimes;
		this.downhillTimes = downhillTimes;
		this.calories = calories;
	}


}
