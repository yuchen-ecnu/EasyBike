package com.pb.json;

public class MobikeDistanceJson {
	String distance;
	int count;
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public MobikeDistanceJson(String distance, int count) {
		super();
		this.distance = distance;
		this.count = count;
	}
	public MobikeDistanceJson() {
		super();
	}
	
}
