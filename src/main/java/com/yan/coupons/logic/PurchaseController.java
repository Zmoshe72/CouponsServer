package com.yan.coupons.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yan.coupons.entity.Coupon;
import com.yan.coupons.entity.Purchase;
import com.yan.coupons.dao.PurchaseDao;
import com.yan.coupons.dto.PurchaseDto;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.enums.Category;
import com.yan.coupons.enums.ErrorType;
import com.yan.coupons.enums.UserType;
import com.yan.coupons.exception.ProgramExceptions;

@Controller
public class PurchaseController {
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private UserController  usersController ;
	
	@Autowired
	private CouponsController  couponController ;
	
	
	public Purchase purchase (PurchaseDto purchaseDto, UserLoginData userLoginData) throws Exception {
		Coupon coupon= couponController.getCouponByName( purchaseDto.getCouponName());
		System.out.println(coupon+ "=========================="+ purchaseDto.getCouponName());
		Purchase purchase= new Purchase(purchaseDto, coupon);
		validPurches(purchase, userLoginData, coupon);
		Calendar cal= Calendar.getInstance();
		purchase.setTimeStamp(cal);
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		try {
			purchase.setUser(this.usersController.getUser(userLoginData));
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

			return purchaseDao.save(purchase);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}
	
	public void update (long idPurchase ,Purchase purchase, UserLoginData userLoginData) throws Exception{
		if (!(userLoginData.getUserType().equals(UserType.Admin))){
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		if (idPurchase != purchase.getIdPurchase()) {
			throw new ProgramExceptions(ErrorType.IdDoesNotMatch);
		}
		if (purchase.getIdPurchase() == 0) {
			throw new ProgramExceptions(ErrorType.NotEnoughDetails);
		}
		try {
		purchaseDao.save(purchase);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}
	
	public void deletePurchesById (long idpurches , UserLoginData userLoginData) throws Exception {
		validDeletePurchesesById(idpurches, userLoginData);
		try {
		purchaseDao.deleteById(idpurches);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}
	
	public List<PurchaseDto> getAllPurchases(UserLoginData userLoginData) throws Exception {
		if (!(userLoginData.getUserType().equals((UserType.Admin)))){
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<PurchaseDto> purchases= new ArrayList<PurchaseDto>();
		try {
			purchases = (List<PurchaseDto>) purchaseDao.getAllPurchases();
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchases ;
	}
	
	public List<PurchaseDto> getPurchasesByUserId (UserLoginData userLoginData, long idUser) throws Exception {
		if (userLoginData.getIdCompany()!= null) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<PurchaseDto> purchases= new ArrayList<PurchaseDto>();
		try {
		purchases = purchaseDao.findByUserId(idUser);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchases;
	}
	
	public List<PurchaseDto> getPurchasesByCategory ( Category category, UserLoginData userLoginData) throws Exception {
		if (userLoginData.getIdCompany()!= null) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<PurchaseDto> purchases= new ArrayList<PurchaseDto>();
		try {
		purchases = purchaseDao.findByCategory(category);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchases;
		}
	
	public List<PurchaseDto> getPurchasesByUserAndCategory (UserLoginData userLoginData, Category category) throws Exception {
		if (userLoginData.getIdCompany()!= null) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<PurchaseDto> purchases= new ArrayList<PurchaseDto>();
		try {
		purchases = purchaseDao.findByCategoryAndUserId(category, userLoginData.getIdUser());
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchases;
	}
	
	public List<PurchaseDto> getPurchasesByCompany (long idCompany, UserLoginData userLoginData) throws Exception {
		if ((userLoginData.getUserType().equals((UserType.Customer)))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<PurchaseDto> purchases= new ArrayList<PurchaseDto>();
		try {
		purchases = purchaseDao.findByCompanyId(idCompany);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchases;
		}
	
	public Purchase getPurchasesById (UserLoginData userLoginData, long idPurches) throws Exception {
		if (userLoginData.getIdCompany()!= null) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		Purchase purchase= new Purchase();
		try {
			purchase = purchaseDao.findById(idPurches);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchase;
	}
	
	//	customer has only one perches and can't get this action
	public List<PurchaseDto> getPurchesesByCouponId (UserLoginData userLoginData, Coupon coupon) throws Exception {
		if ((userLoginData.getUserType().equals((UserType.Customer)))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<PurchaseDto> purchases= new ArrayList<PurchaseDto>();
		try {
		purchases = purchaseDao.findByCouponId (coupon.getId());
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return purchases;
	}
	
	private void validPurches (Purchase purchase, UserLoginData userLoginData, Coupon coupon) throws Exception {
		
		if (!(userLoginData.getUserType().equals(UserType.Customer))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
//		if (purchase.getUser().getId() == userLoginData.getIdUser() ) {
//				throw new ProgramExceptions(ErrorType.CanGetOnlyOnceException);
//		}
		
		//check if there is enough amount
		int amountOfCoupons=coupon.getAmount();
		if (amountOfCoupons < purchase.getAmount()) {
			throw new ProgramExceptions(ErrorType.NotLeftException);
		}
	}
	
	private void validDeletePurchesesById (long idPurches, UserLoginData userLoginData) throws Exception {
		//		only company can delete her coupons, and admin all coupons.
		if ((userLoginData.getUserType().equals(UserType.Customer))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
//		no need to try catch
		if (!(purchaseDao.existsById(idPurches))) {
			throw new ProgramExceptions(ErrorType.IdDoesNotMatch);
		}
	}
	
}
