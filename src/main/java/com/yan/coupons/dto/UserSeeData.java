package com.yan.coupons.dto;

import com.yan.coupons.enums.UserType;

public class UserSeeData {
	
	private String token;
	private UserType userType;
	
	public UserSeeData(String token, UserType userType) {
		super();
		this.token = token;
		this.userType = userType;
	}

	public UserSeeData() {
		super();
	}

	public String getToken() {
		return token;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
}
