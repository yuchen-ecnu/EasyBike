package com.pb.entity;

/**
 * Likes entity. @author MyEclipse Persistence Tools
 */

public class Likes implements java.io.Serializable {

	// Fields

	private Integer id;
	private Active active;
	private User user;
	private String time;

	// Constructors

	/** default constructor */
	public Likes() {
	}

	/** full constructor */
	public Likes(Active active, User user, String time) {
		this.active = active;
		this.user = user;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Active getActive() {
		return this.active;
	}

	public void setActive(Active active) {
		this.active = active;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}