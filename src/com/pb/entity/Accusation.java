package com.pb.entity;

import java.sql.Timestamp;

/**
 * Accusation entity. @author MyEclipse Persistence Tools
 */

public class Accusation implements java.io.Serializable {

	// Fields

	private Integer accusationId;
	private Bike bike;
	private User user;
	private String accusationType;
	private Timestamp time;
	private String addinfo;
	private String state;

	// Constructors

	/** default constructor */
	public Accusation() {
	}

	/** full constructor */
	public Accusation(Bike bike, User user, String accusationType, Timestamp time, String addinfo, String state) {
		this.bike = bike;
		this.user = user;
		this.accusationType = accusationType;
		this.time = time;
		this.addinfo = addinfo;
		this.state = state;
	}

	// Property accessors

	public Integer getAccusationId() {
		return this.accusationId;
	}

	public void setAccusationId(Integer accusationId) {
		this.accusationId = accusationId;
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

	public String getAccusationType() {
		return this.accusationType;
	}

	public void setAccusationType(String accusationType) {
		this.accusationType = accusationType;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getAddinfo() {
		return this.addinfo;
	}

	public void setAddinfo(String addinfo) {
		this.addinfo = addinfo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}