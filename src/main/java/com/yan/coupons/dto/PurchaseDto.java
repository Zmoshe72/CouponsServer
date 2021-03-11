package com.yan.coupons.dto;

import java.util.Calendar;

public class PurchaseDto {
	
	private long idPurchase;
	private String couponName;
	private String userName;
	private String companyName;
	private float price;
	private int amount;
	private Calendar timeStamp;
	
	public PurchaseDto(String couponName, String userName, String companyName, float price, int amount,
			Calendar timeStamp) {
		super();
		this.couponName = couponName;
		this.userName = userName;
		this.companyName = companyName;
		this.price = price;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}

	public PurchaseDto() {
		super();
	}

	public PurchaseDto(long idPurchase, String couponName, String userName, String companyName, float price, int amount,
			Calendar timeStamp) {
		super();
		this.idPurchase = idPurchase;
		this.couponName = couponName;
		this.userName = userName;
		this.companyName = companyName;
		this.price = price;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}

	public String getCouponName() {
		return couponName;
	}

	public String getUserName() {
		return userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public float getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public Calendar getTimeStamp() {
		return timeStamp;
	}
	public long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(long idPurchase) {
		this.idPurchase = idPurchase;
	}
	
	
}
