package com.pb.json;


public class BikeInfo {
	private Integer cityId;
	private Integer positionId;
	private String bikeId;
	private String statues;
	private String type;
	private String password;
	private String latitude;
	private String longitude;
	private Double altitude;
	private String province;
	private String city;
	
	public BikeInfo(Integer cityId, Integer positionId, String bikeId, String statues, String type, String password,
			String latitude, String longitude, Double altitude, String province, String city) {
		super();
		this.cityId = cityId;
		this.positionId = positionId;
		this.bikeId = bikeId;
		this.statues = statues;
		this.type = type;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.province = province;
		this.city = city;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getStatues() {
		return statues;
	}

	public void setStatues(String statues) {
		this.statues = statues;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public BikeInfo(){
		
	}
}
