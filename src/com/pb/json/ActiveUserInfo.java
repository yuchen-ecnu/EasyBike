package com.pb.json;

public class ActiveUserInfo {
	String date;
	String num;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public ActiveUserInfo(String date, String num) {
		super();
		this.date = date;
		this.num = num;
	}
	public ActiveUserInfo() {
		super();
	}
	
}
