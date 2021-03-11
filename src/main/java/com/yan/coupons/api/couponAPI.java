package com.yan.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yan.coupons.dto.CouponDto;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.entity.Coupon;
import com.yan.coupons.enums.Category;
import com.yan.coupons.logic.CouponsController;

@RestController
@RequestMapping("/coupons")
public class couponAPI {

	@Autowired
	CouponsController couponController;
	
	@PostMapping
	public long createCoupon (@RequestBody Coupon coupon) throws Exception {
		couponController.createCoupon(coupon);
		return coupon.getId();
	}
	
	@PutMapping
	public String updateCoupon (@RequestBody Coupon coupon, HttpServletRequest request) throws Exception  {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		couponController.updateCoupon (coupon, userLoginData);
//		return getting message for phase 3
		return "coupon update has succeeded";
	}
	
	@DeleteMapping
	public String deleteCoupon (@PathVariable("idCoupon") long idCoupon, HttpServletRequest request) throws Exception  {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		couponController.deleteCoupon(idCoupon, userLoginData);
//		return getting message for phase 3
		return "coupon has been deleated";
	}
	
	@GetMapping 
	public List<CouponDto> getAllCoupons(HttpServletRequest request) throws Exception  {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<CouponDto> coupons = (List<CouponDto>) couponController.getAllCoupons(userLoginData);
		return coupons;
	}
	
	@GetMapping ("/byCategory")
	public List<CouponDto> getCouponsByCategory (@RequestParam("category") Category category, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<CouponDto> coupons = (List<CouponDto>) couponController.getCouponsByCategory(userLoginData, category);
		return coupons;
	}
	
	@GetMapping ("/byCompanyId") 
	public List<CouponDto> getCouponsByCompanyId (@RequestParam("companyId")  long idCompany, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<CouponDto> coupons = (List<CouponDto>) couponController.getCouponsByCompanyId(userLoginData, idCompany);
		return coupons;
	}
	
	
	@GetMapping ("/{idCoupon}")
	public Coupon getCouponById (@PathVariable("idCoupon") long idCoupon,HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		Coupon coupon =  couponController.getCouponById(userLoginData, idCoupon);
		return coupon;
	}
	
	@GetMapping ("/betweenPrices") 
	public List<CouponDto> getCouponBetweenPrices (@RequestParam("min") float min ,@RequestParam ("max") float max, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<CouponDto> coupons = (List<CouponDto>) couponController.getCouponsBetweenPrices(userLoginData, min, max);
		return coupons;
	}

}
