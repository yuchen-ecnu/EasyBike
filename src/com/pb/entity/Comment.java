package com.pb.entity;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Active active;
	private User user;
	private String content;
	private String time;
	private Integer like;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Active active, User user, String content) {
		this.active = active;
		this.user = user;
		this.content = content;
	}

	/** full constructor */
	public Comment(Active active, User user, String content, String time,
			Integer like) {
		this.active = active;
		this.user = user;
		this.content = content;
		this.time = time;
		this.like = like;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getLike() {
		return this.like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

}