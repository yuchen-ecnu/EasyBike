package com.pb.entity;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */

public class Collection implements java.io.Serializable {

	// Fields

	private Integer id;
	private Active active;
	private String tcid;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** full constructor */
	public Collection(Active active, String tcid) {
		this.active = active;
		this.tcid = tcid;
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

	public String getTcid() {
		return this.tcid;
	}

	public void setTcid(String tcid) {
		this.tcid = tcid;
	}

}