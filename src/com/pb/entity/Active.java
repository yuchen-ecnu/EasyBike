package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Active entity. @author MyEclipse Persistence Tools
 */

public class Active implements java.io.Serializable {

	// Fields

	private Integer id;
	private Host host;
	private String logo;
	private String title;
	private String content;
	private String timeBegin;
	private String timeEnd;
	private String address;
	private Integer like;
	private Integer viewed;
	private String tag;
	@JsonIgnore
	private Set comments = new HashSet(0);
	@JsonIgnore
	private Set likeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Active() {
	}

	/** minimal constructor */
	public Active(Host host, String title, String timeBegin, String timeEnd) {
		this.host = host;
		this.title = title;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
	}

	/** full constructor */
	public Active(Host host, String logo, String title, String content,
			String timeBegin, String timeEnd, String address, Integer like,
			Integer viewed, String tag, Set comments, Set likeses) {
		this.host = host;
		this.logo = logo;
		this.title = title;
		this.content = content;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.address = address;
		this.like = like;
		this.viewed = viewed;
		this.tag = tag;
		this.comments = comments;
		this.likeses = likeses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Host getHost() {
		return this.host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeBegin() {
		return this.timeBegin;
	}

	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}

	public String getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLike() {
		return this.like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getViewed() {
		return this.viewed;
	}

	public void setViewed(Integer viewed) {
		this.viewed = viewed;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getLikeses() {
		return this.likeses;
	}

	public void setLikeses(Set likeses) {
		this.likeses = likeses;
	}

}