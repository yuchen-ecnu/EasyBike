package com.pb.json;

public class BikeUnlock {
	private String bikeId;
	private String statues;
	private String password;
	public BikeUnlock(String bikeId, String statues, String password) {
		super();
		this.bikeId = bikeId;
		this.statues = statues;
		this.password = password;
	}
	public BikeUnlock(){
		
	}
	public String getBikeId() {
		return bikeId;
	}
	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}
	public String getStatues() {
		return statues;
	}
	public void setStatues(String statues) {
		this.statues = statues;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
