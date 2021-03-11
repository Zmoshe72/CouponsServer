package com.yan.coupons.dto;

import java.util.Calendar;

public class CouponDto {
	
	long Id;
	String name;
	String companyName;
	float price;
	String description;
	Calendar endDate;
	
	public CouponDto(long id, String name, String companyName, float price, String description, Calendar endDate) {
		super();
		Id = id;
		this.name = name;
		this.companyName = companyName;
		this.price = price;
		this.description = description;
		this.endDate = endDate;
	}
	

	public CouponDto() {
		super();
	}


	public long getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public float getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Calendar getEndDate() {
		return endDate;
	}
	
	
	
}
