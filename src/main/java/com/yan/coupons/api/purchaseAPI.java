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

import com.yan.coupons.dto.PurchaseDto;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.entity.Coupon;
import com.yan.coupons.entity.Purchase;
import com.yan.coupons.enums.Category;
import com.yan.coupons.logic.PurchaseController;

@RestController
@RequestMapping("/purchases")
public class purchaseAPI {
	
	@Autowired
	PurchaseController purchaseController;
	
	
	@PostMapping
	public long purchase (@RequestBody PurchaseDto purchase, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		Purchase purch = purchaseController.purchase(purchase, userLoginData);
		return purch.getIdPurchase();
	}
	
	@PutMapping("/{idPurchase}")
	public String updatePurchase (@PathVariable("idPurchase") long idPurches ,@RequestBody Purchase purchase, HttpServletRequest request) throws Exception{
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		purchaseController.update(idPurches, purchase, userLoginData);
		return "the update has been made";
	}
	
	@DeleteMapping ("/{idPurchase}")
	public String deletePurchesById (@PathVariable("idPurchase") long idPurchase , HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		purchaseController.deletePurchesById(idPurchase, userLoginData);
//		return getting message for phase 3
		return "the delete has been made";
	}
	
	@GetMapping 
	public List<PurchaseDto> getAllPurchases ( HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return purchaseController.getAllPurchases(userLoginData);
	}
	
	@GetMapping ("/byUserId") 
	public List<PurchaseDto> getPurchesesByUserId (@RequestParam("userId")  long idUser,   HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<PurchaseDto> purchases=  (List<PurchaseDto>) purchaseController.getPurchasesByUserId (userLoginData, idUser);
		return purchases;
	}
	
	@GetMapping  ("/byCategory") 
	public List<PurchaseDto> getPurchesesByCategory (@RequestParam("category")  Category category,  HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return purchaseController.getPurchasesByCategory( category, userLoginData);
	}
	
	@GetMapping  ("/userAndCategory")
	public List<PurchaseDto> getPurchasesByUserAndCategory (@RequestParam ("category") Category category, @RequestParam("userId")  long idUser , HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return purchaseController.getPurchasesByUserAndCategory(userLoginData, category);
	}
	
	@GetMapping  ("/byCompany") 
	public List<PurchaseDto> getPurchasesByCompany (@RequestParam ("idCompany")  long idCompany,  HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return purchaseController.getPurchasesByCompany(idCompany, userLoginData);
	}
	
	@GetMapping("/{idPurchase}")
	public Purchase getPurchasesById (@PathVariable("idPurchase") long idPurchase, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return purchaseController.getPurchasesById(userLoginData, idPurchase);
	}
	
	@GetMapping	("/byCoupon")
	public List<PurchaseDto> getPurchesesByCouponId (@RequestParam("couponId") Coupon coupon, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return purchaseController.getPurchesesByCouponId(userLoginData, coupon);
	}
}
