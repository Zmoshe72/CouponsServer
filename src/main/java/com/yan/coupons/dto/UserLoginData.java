package com.yan.coupons.dto;


import com.yan.coupons.enums.UserType;


public class UserLoginData {
	
	
	private long idUser;
	private Long idCompany;
	private UserType userType;
	
	public UserLoginData(long idUser, Long idCompany,UserType userType2) {
		super();
		this.idUser = idUser;
		this.idCompany = idCompany;
		this.userType = userType2;
	}
	
	public UserLoginData() {
		
	}
	
	public long getIdUser() {
		return idUser;
	}
	public Long getIdCompany() {
		return idCompany;
	}
	public UserType getUserType() {
		return userType;
	}

	@Override
	public String toString() {
		return "UserLoginData [idUser=" + idUser + ", idCompany=" + idCompany + ", userType=" + userType + "]";
	}
	
	
	
	
}
