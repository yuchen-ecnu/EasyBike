package com.pb.json;

public class CollectingInfo {
	private String header;
	private String hostName;
	private String location;
	private String activeName;
	private String timeStart;
	private String timeEnd;
	private String activeId;
	private String poster;
	
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getActiveId() {
		return activeId;
	}
	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public CollectingInfo(String header, String hostName, String location,
			String activeName, String timeStart, String timeEnd) {
		super();
		this.header = header;
		this.hostName = hostName;
		this.location = location;
		this.activeName = activeName;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	public CollectingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
