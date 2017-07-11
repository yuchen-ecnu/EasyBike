package com.pb.json;

public class RideTimes {
	private String day;
	private String times;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public RideTimes(String day, String times) {
		super();
		this.day = day;
		this.times = times;
	}
	public RideTimes() {
		super();
	}
	
}
