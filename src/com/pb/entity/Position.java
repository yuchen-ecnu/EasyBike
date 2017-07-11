package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Integer positionId;
	private Trip trip;
	private City city;
	private String latitude;
	private String longitude;
	private Double altitude;
	@JsonIgnore
	private Set bikes = new HashSet(0);
	@JsonIgnore
	private Set tripsForStartPoint = new HashSet(0);
	@JsonIgnore
	private Set tripsForEndPoint = new HashSet(0);

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** full constructor */
	public Position(Trip trip, City city, String latitude, String longitude, Double altitude, Set bikes,
			Set tripsForStartPoint, Set tripsForEndPoint) {
		this.trip = trip;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.bikes = bikes;
		this.tripsForStartPoint = tripsForStartPoint;
		this.tripsForEndPoint = tripsForEndPoint;
	}

	// Property accessors

	public Integer getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Set getBikes() {
		return this.bikes;
	}

	public void setBikes(Set bikes) {
		this.bikes = bikes;
	}

	public Set getTripsForStartPoint() {
		return this.tripsForStartPoint;
	}

	public void setTripsForStartPoint(Set tripsForStartPoint) {
		this.tripsForStartPoint = tripsForStartPoint;
	}

	public Set getTripsForEndPoint() {
		return this.tripsForEndPoint;
	}

	public void setTripsForEndPoint(Set tripsForEndPoint) {
		this.tripsForEndPoint = tripsForEndPoint;
	}

}