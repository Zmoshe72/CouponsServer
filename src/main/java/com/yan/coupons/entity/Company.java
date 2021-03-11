package com.yan.coupons.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name = "company")
public class Company {
	
	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;
	
	@Column (name = "name", unique = true , nullable = false)
	private String name;
	
	@Column (name = "adress")
	private String adress;
	
	@Column (name = "phone")
	private String phone;
	
	
	@OneToMany (mappedBy = "company")
	private List<User> users;
	
	
	@OneToMany	(mappedBy = "company")
	private List<Coupon> coupons;

	public Company(long id, String name, String adress, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
	}
	
	public Company(String name, String adress, String phone) {
		super();
		this.name = name;
		this.adress = adress;
		this.phone = phone;
	}

	public Company(List<Coupon> coupons) {
		super();
		this.coupons = coupons;
	}

	public Company() {
		super();
	}


	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@Override
	public String toString() {
		return "Company id= " + id + ", name= " + name + ", adress= " + adress + ", phone= " + phone + ".";
	}
	
}
