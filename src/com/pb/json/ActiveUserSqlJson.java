package com.pb.json;

import java.sql.Timestamp;

public class ActiveUserSqlJson {
	Timestamp starttime;
	Integer userid;
	public Timestamp getTime() {
		return starttime;
	}
	public void setTime(Timestamp time) {
		this.starttime = time;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public ActiveUserSqlJson(Timestamp time, Integer userid) {
		super();
		this.starttime = time;
		this.userid = userid;
	}
	public ActiveUserSqlJson() {
		super();
	}
	
}
