package com.pb.json;

public class UserIncreaseInfo {
	String date;
	int number;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public UserIncreaseInfo() {
		super();
	}
	public UserIncreaseInfo(String date, int number) {
		super();
		this.date = date;
		this.number = number;
	}
	
	
}
