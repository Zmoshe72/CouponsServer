package com.yan.coupons.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yan.coupons.dto.PurchaseDto;

@Entity
@Table (name = "purchases")
public class Purchase {
	
	@Id
	@GeneratedValue
	@Column (name = "id")
	private long idPurchase;
	
	@Column (name = "amount_of_purchase" , nullable = false )
	private int amount;
	
	@Column (name = "time_stamp")
	private Calendar timeStamp;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	
	@ManyToOne
	private Coupon coupon;
	

	public Purchase(long idPurchase, int amount, Calendar timeStamp, User user, Coupon coupon) {
		super();
		this.idPurchase = idPurchase;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.user = user;
		this.coupon = coupon;
	}
	
	
	public Purchase(PurchaseDto purchaseDto, Coupon coupon) {
		super();
		this.idPurchase = purchaseDto.getIdPurchase();
		this.amount = purchaseDto.getAmount();
		this.coupon = coupon;
	}


	// for purchase
	public Purchase( int amount, User user, Coupon coupon) {
		super();
		this.amount = amount;
		this.user = user;
		this.coupon = coupon;
	}

	public Purchase() {
		super();
	}
	

	public long getIdPurchase() {
		return idPurchase;
	}

	public int getAmount() {
		return amount;
	}

	public Calendar getTimeStamp() {
		return timeStamp;
	}

	public User getUser() {
		return user;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setIdPurchase(long idPurchase) {
		this.idPurchase = idPurchase;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setTimeStamp(Calendar timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Purchase [idPurchase=" + idPurchase + ", amount=" + amount + ", timeStamp=" + timeStamp + ", user="
				+ user + ", coupon=" + coupon + "]";
	}
	
	
}
