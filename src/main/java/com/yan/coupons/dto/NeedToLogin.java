package com.yan.coupons.dto;


public class NeedToLogin {
	
	private String userName;
	private String password;
	
	public NeedToLogin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	public NeedToLogin() {
		super();
	}


	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
