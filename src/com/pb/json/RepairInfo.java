package com.pb.json;

import java.sql.Timestamp;

import com.pb.entity.Bike;

public class RepairInfo {
	private String bikeId;
	private Integer userId;
	private String type;
	private String latitude;
	private String longitude;
	private Integer repairId;
	private String repairInformation;
	private Timestamp reportTime;
	private Timestamp repairTime;
	private String addinfo;
	private String name;
	public String getBikeId() {
		return bikeId;
	}
	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Integer getRepairId() {
		return repairId;
	}
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	public String getRepairInformation() {
		return repairInformation;
	}
	public void setRepairInformation(String repairInformation) {
		this.repairInformation = repairInformation;
	}
	public Timestamp getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public Timestamp getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Timestamp repairTime) {
		this.repairTime = repairTime;
	}
	public String getAddinfo() {
		return addinfo;
	}
	public void setAddinfo(String addinfo) {
		this.addinfo = addinfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserId() {
		return userId;
	}
	public RepairInfo(String bikeId, Integer userId, String type, String latitude, String longitude, Integer repairId,
			String repairInformation, Timestamp reportTime, Timestamp repairTime, String addinfo, String name) {
		super();
		this.bikeId = bikeId;
		this.userId = userId;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.repairId = repairId;
		this.repairInformation = repairInformation;
		this.reportTime = reportTime;
		this.repairTime = repairTime;
		this.addinfo = addinfo;
		this.name = name;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public RepairInfo() {
		super();
	}
}
