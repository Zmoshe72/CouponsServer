package com.yan.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yan.coupons.dto.PurchaseDto;
import com.yan.coupons.entity.Purchase;
import com.yan.coupons.enums.Category;

@Repository
public interface PurchaseDao extends CrudRepository<Purchase, Long> {

	@Query ("select new com.yan.coupons.dto.PurchaseDto (p.coupon.name, p.user.name, p.coupon.company.name, p.coupon.price , p.coupon.amount , p.timeStamp)"
			+ " from Purchase p where p.user.id = ?1")
	public List<PurchaseDto> findByUserId(long userId);
	
	@Query ("select new com.yan.coupons.dto.PurchaseDto (p.coupon.name, p.user.name, p.coupon.company.name, p.coupon.price , p.coupon.amount , p.timeStamp) from Purchase p")
	public List<PurchaseDto> getAllPurchases(); 
	
//	need for validation because Purchase is needed.
	public Purchase findById(long id);

	@Query ("select new com.yan.coupons.dto.PurchaseDto (p.coupon.name, p.user.name, p.coupon.company.name, p.coupon.price , p.coupon.amount , p.timeStamp) from Purchase p "
			+ "left outer join Coupon c    on p.coupon.id = c.id   where c.category = ?1")
	public List<PurchaseDto> findByCategory(Category category);
	
	@Query ("select new com.yan.coupons.dto.PurchaseDto (p.coupon.name, p.user.name, p.coupon.company.name, p.coupon.price , p.amount , p.timeStamp)"
			+ " from Purchase p left outer join Coupon c    on p.coupon.id = c.id   where c.category = ?1 and p.user.id =?2")
	public List<PurchaseDto> findByCategoryAndUserId(Category category, long userId);

	@Query ("select new com.yan.coupons.dto.PurchaseDto (p.coupon.name, p.user.name, p.coupon.company.name, p.coupon.price , p.coupon.amount , p.timeStamp)"
			+ " from Purchase p   where  p.coupon.company.id =?1")
	public List<PurchaseDto> findByCompanyId(long companyId);

	@Query ("select new com.yan.coupons.dto.PurchaseDto (p.coupon.name, p.user.name, p.coupon.company.name, p.coupon.price , p.coupon.amount , p.timeStamp)"
			+ " from Purchase p   where  p.coupon.id =?1")
	public List<PurchaseDto> findByCouponId(long couponId);
	
	

	
	
	
	

}
