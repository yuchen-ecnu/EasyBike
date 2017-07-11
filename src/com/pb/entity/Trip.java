package com.pb.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Trip entity. @author MyEclipse Persistence Tools
 */

public class Trip implements java.io.Serializable {

	// Fields

	private Integer tripId;
	private Position positionByEndPoint;
	private Bike bike;
	private User user;
	private Position positionByStartPoint;
	private Timestamp startTime;
	private Timestamp endTime;
	private Double avgSpeed;
	private Double totalLength;
	private Integer uphillTimes;
	private Integer downhillTimes;
	private Integer calories;
	@JsonIgnore
	private Set positions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Trip() {
	}

	/** full constructor */
	public Trip(Position positionByEndPoint, Bike bike, User user, Position positionByStartPoint, Timestamp startTime,
			Timestamp endTime, Double avgSpeed, Double totalLength, Integer uphillTimes, Integer downhillTimes,
			Integer calories, Set positions) {
		this.positionByEndPoint = positionByEndPoint;
		this.bike = bike;
		this.user = user;
		this.positionByStartPoint = positionByStartPoint;
		this.startTime = startTime;
		this.endTime = endTime;
		this.avgSpeed = avgSpeed;
		this.totalLength = totalLength;
		this.uphillTimes = uphillTimes;
		this.downhillTimes = downhillTimes;
		this.calories = calories;
		this.positions = positions;
	}

	// Property accessors

	public Integer getTripId() {
		return this.tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Position getPositionByEndPoint() {
		return this.positionByEndPoint;
	}

	public void setPositionByEndPoint(Position positionByEndPoint) {
		this.positionByEndPoint = positionByEndPoint;
	}

	public Bike getBike() {
		return this.bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Position getPositionByStartPoint() {
		return this.positionByStartPoint;
	}

	public void setPositionByStartPoint(Position positionByStartPoint) {
		this.positionByStartPoint = positionByStartPoint;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Double getAvgSpeed() {
		return this.avgSpeed;
	}

	public void setAvgSpeed(Double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public Double getTotalLength() {
		return this.totalLength;
	}

	public void setTotalLength(Double totalLength) {
		this.totalLength = totalLength;
	}

	public Integer getUphillTimes() {
		return this.uphillTimes;
	}

	public void setUphillTimes(Integer uphillTimes) {
		this.uphillTimes = uphillTimes;
	}

	public Integer getDownhillTimes() {
		return this.downhillTimes;
	}

	public void setDownhillTimes(Integer downhillTimes) {
		this.downhillTimes = downhillTimes;
	}

	public Integer getCalories() {
		return this.calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Set getPositions() {
		return this.positions;
	}

	public void setPositions(Set positions) {
		this.positions = positions;
	}

}