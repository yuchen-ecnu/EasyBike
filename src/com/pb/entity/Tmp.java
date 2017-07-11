package com.pb.entity;

import java.sql.Timestamp;

/**
 * Tmp entity. @author MyEclipse Persistence Tools
 */

public class Tmp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time;
	private String bikeid;
	private String lon;
	private String lat;
	private Integer biketype;

	// Constructors

	/** default constructor */
	public Tmp() {
	}

	/** minimal constructor */
	public Tmp(Timestamp time, String bikeid, String lon, String lat) {
		this.time = time;
		this.bikeid = bikeid;
		this.lon = lon;
		this.lat = lat;
	}

	/** full constructor */
	public Tmp(Timestamp time, String bikeid, String lon, String lat, Integer biketype) {
		this.time = time;
		this.bikeid = bikeid;
		this.lon = lon;
		this.lat = lat;
		this.biketype = biketype;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getBikeid() {
		return this.bikeid;
	}

	public void setBikeid(String bikeid) {
		this.bikeid = bikeid;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Integer getBiketype() {
		return this.biketype;
	}

	public void setBiketype(Integer biketype) {
		this.biketype = biketype;
	}

}