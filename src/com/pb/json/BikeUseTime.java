package com.pb.json;

public class BikeUseTime {
	String TimeZone;
	String times;
	public String getTimeZone() {
		return TimeZone;
	}
	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public BikeUseTime(String timeZone, String times) {
		super();
		TimeZone = timeZone;
		this.times = times;
	}
	public BikeUseTime() {
		super();
	}
	
}
