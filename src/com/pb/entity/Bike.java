package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Bike entity. @author MyEclipse Persistence Tools
 */

public class Bike implements java.io.Serializable {

	// Fields

	private String bikeId;
	private Position position;
	private String statues;
	private String type;
	private String password;
	@JsonIgnore
	private Set trips = new HashSet(0);
	@JsonIgnore
	private Set accusations = new HashSet(0);
	@JsonIgnore
	private Set repairs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Bike() {
	}

	/** full constructor */
	public Bike(Position position, String statues, String type, String password, Set trips, Set accusations,
			Set repairs) {
		this.position = position;
		this.statues = statues;
		this.type = type;
		this.password = password;
		this.trips = trips;
		this.accusations = accusations;
		this.repairs = repairs;
	}

	// Property accessors

	public String getBikeId() {
		return this.bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getStatues() {
		return this.statues;
	}

	public void setStatues(String statues) {
		this.statues = statues;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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