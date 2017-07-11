package com.pb.entity;

import java.sql.Timestamp;

/**
 * Repair entity. @author MyEclipse Persistence Tools
 */

public class Repair implements java.io.Serializable {

	// Fields

	private Integer repairId;
	private Bike bike;
	private User user;
	private String repairInformation;
	private Timestamp reportTime;
	private Timestamp repairTime;
	private String addinfo;

	// Constructors

	/** default constructor */
	public Repair() {
	}

	/** full constructor */
	public Repair(Bike bike, User user, String repairInformation, Timestamp reportTime, Timestamp repairTime,
			String addinfo) {
		this.bike = bike;
		this.user = user;
		this.repairInformation = repairInformation;
		this.reportTime = reportTime;
		this.repairTime = repairTime;
		this.addinfo = addinfo;
	}

	// Property accessors

	public Integer getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
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

	public String getRepairInformation() {
		return this.repairInformation;
	}

	public void setRepairInformation(String repairInformation) {
		this.repairInformation = repairInformation;
	}

	public Timestamp getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public Timestamp getRepairTime() {
		return this.repairTime;
	}

	public void setRepairTime(Timestamp repairTime) {
		this.repairTime = repairTime;
	}

	public String getAddinfo() {
		return this.addinfo;
	}

	public void setAddinfo(String addinfo) {
		this.addinfo = addinfo;
	}

}