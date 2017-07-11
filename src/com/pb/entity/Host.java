package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Host entity. @author MyEclipse Persistence Tools
 */

public class Host implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hostName;
	private String account;
	private String password;
	private String address;
	private String hostType;
	private String contact;
	private String intro;
	private String header;
	private Set actives = new HashSet(0);

	// Constructors

	/** default constructor */
	public Host() {
	}

	/** minimal constructor */
	public Host(String hostName, String account, String password,
			String hostType) {
		this.hostName = hostName;
		this.account = account;
		this.password = password;
		this.hostType = hostType;
	}

	/** full constructor */
	public Host(String hostName, String account, String password,
			String address, String hostType, String contact, String intro,
			String header, Set actives) {
		this.hostName = hostName;
		this.account = account;
		this.password = password;
		this.address = address;
		this.hostType = hostType;
		this.contact = contact;
		this.intro = intro;
		this.header = header;
		this.actives = actives;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHostType() {
		return this.hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Set getActives() {
		return this.actives;
	}

	public void setActives(Set actives) {
		this.actives = actives;
	}

}