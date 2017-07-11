package com.pb.json;

public class MobikeTypeJson {
	String mobikeType;
	String count;
	public String getMobikeType() {
		return mobikeType;
	}
	public void setMobikeType(String mobikeType) {
		this.mobikeType = mobikeType;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public MobikeTypeJson(String mobikeType, String count) {
		super();
		this.mobikeType = mobikeType;
		this.count = count;
	}
	public MobikeTypeJson() {
		super();
	}
	
}
