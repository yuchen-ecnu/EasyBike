package com.pb.json;

import com.pb.entity.Host;

public class ActiveJson {
	private Integer id;
	private String hostname;
	private String hostContact;
	private String hostInfo;
	private String logo;
	private String title;
	private String content;
	private String timeBegin;
	private String timeEnd;
	private String address;
	private Integer like;
	private Integer viewed;
	private String tag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimeBegin() {
		return timeBegin;
	}
	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Integer getViewed() {
		return viewed;
	}
	public void setViewed(Integer viewed) {
		this.viewed = viewed;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getHostContact() {
		return hostContact;
	}
	public void setHostContact(String hostContact) {
		this.hostContact = hostContact;
	}
	public String getHostInfo() {
		return hostInfo;
	}
	public void setHostInfo(String hostInfo) {
		this.hostInfo = hostInfo;
	}
	public ActiveJson(Integer id, String hostname, String hostContact,
			String hostInfo, String logo, String title, String content,
			String timeBegin, String timeEnd, String address, Integer like,
			Integer viewed, String tag) {
		super();
		this.id = id;
		this.hostname = hostname;
		this.hostContact = hostContact;
		this.hostInfo = hostInfo;
		this.logo = logo;
		this.title = title;
		this.content = content;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.address = address;
		this.like = like;
		this.viewed = viewed;
		this.tag = tag;
	}
	public ActiveJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
