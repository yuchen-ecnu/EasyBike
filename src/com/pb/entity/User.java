package com.pb.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String name;
	private String gender;
	private String password;
	private String phone;
	private String userType;
	private Timestamp registetime;
	@JsonIgnore
	private Set trips = new HashSet(0);
	@JsonIgnore
	private Set accusations = new HashSet(0);
	@JsonIgnore
	private Set repairs = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, String gender, String password, String phone, String userType, Timestamp registetime,
			Set trips, Set accusations, Set repairs) {
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
		this.registetime = registetime;
		this.trips = trips;
		this.accusations = accusations;
		this.repairs = repairs;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Timestamp getRegistetime() {
		return this.registetime;
	}

	public void setRegistetime(Timestamp registetime) {
		this.registetime = registetime;
	}

	public Set getTrips() {
		return this.trips;
	}

	public void setTrips(Set trips) {
		this.trips = trips;
	}

	public Set getAccusations() {
		return this.accusations;
	}

	public void setAccusations(Set accusations) {
		this.accusations = accusations;
	}

	public Set getRepairs() {
		return this.repairs;
	}

	public void setRepairs(Set repairs) {
		this.repairs = repairs;
	}

}