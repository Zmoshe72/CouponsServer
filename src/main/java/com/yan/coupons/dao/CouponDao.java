package com.yan.coupons.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yan.coupons.dto.CouponDto;
import com.yan.coupons.entity.Coupon;
import com.yan.coupons.enums.Category;

@Repository
public interface CouponDao extends CrudRepository<Coupon, Long>{
	
	@Query ("select new com.yan.coupons.dto.CouponDto(c.id, c.name, c.company.name, c.price , c.description , c.endDate) from Coupon c where c.category = ?1 ")
	public List<CouponDto> findByCategory(Category category);

	@Query ("select new com.yan.coupons.dto.CouponDto(c.id, c.name, c.company.name, c.price , c.description , c.endDate) from Coupon c where c.company.id = ?1 ")
	public List<CouponDto> findByCompanyId( long id);
	
	@Query ("select new com.yan.coupons.dto.CouponDto(c.id, c.name, c.company.name, c.price , c.description , c.endDate) from Coupon c where c.price > ?1 and c.price < ?2")
	public List<CouponDto> findCouponBetweenPrices ( float min,  float max);

	public boolean existsByName(String name);
	
	@Query ("select new com.yan.coupons.dto.CouponDto(c.id, c.name, c.company.name, c.price , c.description , c.endDate) from Coupon c ")
	public List<CouponDto> getAllCoupons();
	
	@Query ("select c from Coupon c where c.endDate < ?1")
	public List<Coupon> findCouponsExpired(Calendar dateNow);
	
//	need for validations
	public Coupon findById(long id);

	public Coupon findByName(String name);
}
