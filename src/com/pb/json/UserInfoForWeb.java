package com.pb.json;

public class UserInfoForWeb {
		private Integer userId;
		private String name;
		private String gender;
		private String password;
		private String phone;
		private String userType;
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public UserInfoForWeb(Integer userId, String name, String gender,
				String password, String phone, String userType) {
			super();
			this.userId = userId;
			this.name = name;
			this.gender = gender;
			this.password = password;
			this.phone = phone;
			this.userType = userType;
		}
		public UserInfoForWeb(){
			
		}
}
