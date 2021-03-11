package com.yan.coupons.dto;

public class ErrorBean {
	
	private int errorNum;
	private String name;
	private String message;
	
	
	public ErrorBean(int errorNum, String name, String message) {
		super();
		this.errorNum = errorNum;
		this.name = name;
		this.message = message;
	}


	public int getErrorNum() {
		return errorNum;
	}


	public String getName() {
		return name;
	}


	public String getMessage() {
		return message;
	}


	@Override
	public String toString() {
		return "ErrorBean [errorNum=" + errorNum + ", name=" + name + ", message=" + message + "]";
	}
	
	
}
