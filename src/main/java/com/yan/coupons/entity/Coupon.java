package com.yan.coupons.entity;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yan.coupons.enums.Category;
@Entity
@Table (name = "coupons")
public class Coupon {
	
	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;
	
	@Column (name = "name", unique = true , nullable = false)
	private String name;
	
	@Column (name = "price", nullable = false)
	private float price;
	
	@Column (name = "description")
	private String description;
	
	@Column (name = "start_Date")
	private Calendar startDate;
	
	@Column (name = "expire_Date", nullable = false)
	private Calendar endDate;
	
	@Enumerated (EnumType.STRING)
	@Column (name = "category", nullable = false)
	private Category category;
	
	@Column (name = "amount_of_coupons", nullable = false)
	private int amount;
	
	@Column (name = "image", unique = true)
	private String image;
	
	@JsonIgnore
	@OneToMany (mappedBy = "coupon", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;
	
	@ManyToOne
	private Company company;
	
	public Coupon( String name, float price, String discription, Calendar startDate, Calendar endDate,
			Category category, int amount, String image, long companyId) {
		super();
		this.name = name;
		this.price = price;
		this.description = discription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.amount = amount;
		this.image = image;
//		this.companyId = companyId;

	}

	public Coupon(long id, String name, float price, String discription, Calendar startDate, Calendar endDate,
			Category category, int amount, String image, long companyId) {
		this(name,price,discription,startDate,endDate,category,amount,image, companyId);
		this.id = id;
	}

	public Coupon() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	//	the parameter timeStamp need to be Calendar for sql need date. that function replace them.
	public Date getStartDate() {
		long con= this.startDate.getTimeInMillis();
		Date d=new Date(con);
		return d;
	}

	//	the parameter timeStamp need to be Calendar for sql need date. that function replace them.
	public Date getEndDate() {
		long con= this.endDate.getTimeInMillis();
		Date d=new Date(con);
		return d;	}

	public Category getCategory() {
		return category;
	}

	public int getAmount() {
		return amount;
	}

	public String getImage() {
		return image;
	}
	
//	public long getCompanyId () {
//		return this.getCompanyId();
//	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setDiscription(String discription) {
		this.description = discription;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
//	public void setCompanyId(long companyId) {
//		this.companyId = companyId;
//	}

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

	//	replace the string to enum.
	public Category setCategoryFromString (String category) throws Exception {
		if (category.equalsIgnoreCase((Category.ATTRACTIONS).name()))
			return Category.ATTRACTIONS;
		if (category.equalsIgnoreCase((Category.ELECTRICITY).name()))
			return Category.ELECTRICITY;
		if (category.equalsIgnoreCase((Category.FOOD).name()))
			return Category.FOOD;
		if (category.equalsIgnoreCase((Category.GADGETS).name()))
			return Category.GADGETS;
		if (category.equalsIgnoreCase((Category.HOME).name()))
			return Category.HOME;
		if (category.equalsIgnoreCase((Category.TOOLS).name()))
			return Category.TOOLS;
		throw new Exception("the catgory doesn't exist");
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		//		didn't to invest in print because it's temporary and will change.
		return "Coupons id= " + id + ", name= " + name + ", price= " + price + ", discription= " + description
				+ ", startDate= " + startDate + ", endDate= " + endDate+ ", category= " + category + ", amount= " + amount
				+ ", image= " + image + ", companyId= "  + ".";
	}


}

