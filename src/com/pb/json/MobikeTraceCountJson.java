package com.pb.json;

import java.sql.Timestamp;

public class MobikeTraceCountJson {
	private Integer mobikeid;
	private Timestamp time;
	private Integer biketype;
	private String distid;
	private String lon;
	private String lat;
	private Integer tripcount;
	public Integer getMobikeid() {
		return mobikeid;
	}
	public void setMobikeid(Integer mobikeid) {
		this.mobikeid = mobikeid;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getBiketype() {
		return biketype;
	}
	public void setBiketype(Integer biketype) {
		this.biketype = biketype;
	}
	public String getDistid() {
		return distid;
	}
	public void setDistid(String distid) {
		this.distid = distid;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public Integer getTripcount() {
		return tripcount;
	}
	public void setTripcount(Integer tripcount) {
		this.tripcount = tripcount;
	}
	public MobikeTraceCountJson(Integer mobikeid, Timestamp time, Integer biketype, String distid, String lon,
			String lat, Integer tripcount) {
		super();
		this.mobikeid = mobikeid;
		this.time = time;
		this.biketype = biketype;
		this.distid = distid;
		this.lon = lon;
		this.lat = lat;
		this.tripcount = tripcount;
	}
	public MobikeTraceCountJson() {
		super();
	}
	
}
