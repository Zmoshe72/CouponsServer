package com.yan.coupons.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yan.coupons.enums.UserType;

@Entity
@Table (name = "user")
public class User {
	
	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;
	
	@Column (name = "user_name", unique = true, nullable = false)
	private String name;
	
	@Column (name = "password", nullable = false)
	private String password;
		
	@Enumerated (EnumType.STRING)
	@Column (name = "user_type", nullable = false)
	private UserType userType;
	
	@JsonIgnore
	@OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;
	
	@JsonIgnore
	@ManyToOne (fetch = FetchType.EAGER)
	private Company company;
	
		
	public User( String name, String password ,UserType userType) {
		this.name = name;
		this.password = password;
		this.userType = userType;
	}


	public User(long id,String name, String password,Long companyId, UserType userType) {
		this(name, password,  userType);
		this.id = id;
	}
	public User() {
		super();
	}

	

	public List<Purchase> getPurchases() {
		return purchases;
	}


	public Company getCompany() {
		return company;
	}


	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public long getId() {
		return id;
	}


	public String getname() {
		return name;
	}


	public String getPassword() {
		return password;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setUserName(String userName) {
		this.name = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	@Override
	public String toString() {
		return "User  the id=" + id + ", userName=" + name + ", password=" + password + ", companyId=" 
				+ ", userType=" + userType + ".";
	}
	
}
