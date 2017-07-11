package com.pb.json;

public class Point {
	private Integer tripId;
	private Integer positionId;
	private String latitude;
	private String longitude;
	private Double altitude;
	private String province;
	private String city;
	
	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Point(Integer tripId, Integer positionId, String latitude, String longitude, Double altitude,
			String province, String city) {
		super();
		this.tripId = tripId;
		this.positionId = positionId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.province = province;
		this.city = city;
	}
	public Point() {
	}

}
