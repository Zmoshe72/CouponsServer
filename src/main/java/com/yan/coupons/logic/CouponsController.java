package com.yan.coupons.logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yan.coupons.dao.CompanyDao;
import com.yan.coupons.dao.CouponDao;
import com.yan.coupons.dto.CouponDto;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.entity.Coupon;
import com.yan.coupons.enums.Category;
import com.yan.coupons.enums.ErrorType;
import com.yan.coupons.enums.UserType;
import com.yan.coupons.exception.ProgramExceptions;

@Controller
public class CouponsController {

	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	public CouponsController() {
	}

	public Coupon createCoupon (Coupon coupon) throws Exception {
		validCreateCoupon(coupon);
		Coupon couponTemp = new Coupon();
		try {
		couponTemp = couponDao.save(coupon);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return couponTemp;
	}
	
	public void updateCoupon (Coupon coupon, UserLoginData userLoginData) throws Exception {
		validUniqueUpdate( coupon,  userLoginData);
		validCreateCoupon(coupon);
		try {
		couponDao.save(coupon);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}

	public void deleteCoupon (long idCoupon, UserLoginData userLoginData) throws Exception {
		if ((userLoginData.getUserType() .equals(UserType.Customer)))
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		// no need to try catch
		if (!(couponDao.existsById(idCoupon)))
			throw new ProgramExceptions(ErrorType.NoCouponByDetailsProvidedException);
		try {
		couponDao.deleteById(idCoupon);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}
	
	public List<CouponDto> getAllCoupons(UserLoginData userLoginData) throws Exception {
		if ((userLoginData.getUserType().equals((UserType.Company)))){
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<CouponDto> coupons = new ArrayList<CouponDto>();
		try {
		coupons = (List<CouponDto>) couponDao.getAllCoupons();
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return coupons;
	}
	
	public List<CouponDto> getCouponsByCategory (UserLoginData userLoginData, Category category) throws Exception {
		if ((userLoginData.getUserType().equals((UserType.Company)))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<CouponDto> coupons = new ArrayList<CouponDto>();
		try {
		coupons = couponDao.findByCategory(category);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return coupons;
	}
	
	public List<CouponDto> getCouponsByCompanyId (UserLoginData userLoginData, long companyId) throws Exception {
		if ((userLoginData.getUserType().equals((UserType.Customer)))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		List<CouponDto> coupons = new ArrayList<CouponDto>();
		try {
		coupons = couponDao.findByCompanyId(companyId);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return coupons;
	}
	
	public Coupon getCouponById (UserLoginData userLoginData, long idCoupon) throws Exception {
		if ((userLoginData.getUserType().equals((UserType.Company)))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		Coupon coupon = (new Coupon());
		try {
		coupon = couponDao.findById(idCoupon);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		System.out.println("i got here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ coupon.getDescription()+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return coupon;
	}
	
	public List<CouponDto> getCouponsBetweenPrices (UserLoginData userLoginData, float min , float max) throws Exception {
		validGetCouponBetwinPrices ( userLoginData,  min , max);
		List<CouponDto> coupons = new ArrayList<CouponDto>();
		try {
		coupons =couponDao.findCouponBetweenPrices(min, max);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return coupons;
	}
	
	private void validCreateCoupon(Coupon coupon ) throws Exception {
		if (coupon.getName() == null) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}
		if (coupon.getName().isEmpty()) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}		
		
		if (coupon.getName().length() <2) {
			throw new ProgramExceptions(ErrorType.ShortNameException);
		}
		
		// no need try catch
		if (couponDao.existsByName(coupon.getName())) {
			throw new ProgramExceptions(ErrorType.NameAlreadyExistException);
		}
		
		if (coupon.getPrice()<0) {
			throw new ProgramExceptions(ErrorType.IllegalPriceException);
		}
		
		Date date= new Date(Calendar.getInstance().getTimeInMillis());
		if (coupon.getEndDate().compareTo(date)<0) {
			throw new ProgramExceptions(ErrorType.ExpiredDateException );
		}
		if (coupon.getStartDate().compareTo(date)<0) {
			throw new ProgramExceptions(ErrorType.ExpiredDateException);
		}
		if (coupon.getAmount()<=0) {
			throw new ProgramExceptions(ErrorType.AmountException);
		}
		
		coupon.getCategory();
		if (!(coupon.getCategory().equals(Category.ATTRACTIONS)) && !(coupon.getCategory().equals(Category.ELECTRICITY)) && 
				!(coupon.getCategory().equals(Category.FOOD)) && !(coupon.getCategory().equals(Category.GADGETS)) &&
				!(coupon.getCategory().equals(Category.HOME)) && !(coupon.getCategory().equals(Category.TOOLS))) {
			throw new ProgramExceptions(ErrorType.IllegalCategoryException);
		}
		
		//no need try catch
		if (!(companyDao.existsCompanyById(coupon.getCompany().getId()))) {
			throw new Exception("there is no such company by id.");
		}
		
		if (coupon.getCompany().getId()==0) {
			throw new ProgramExceptions(ErrorType.NoCompanyByDetailsProvidedException);
		}
	}
	
	private void validUniqueUpdate(Coupon coupon, UserLoginData userLoginData) throws Exception {
		try {
		if(!(couponDao.findById(coupon.getId()) == null)) {
			throw new ProgramExceptions(ErrorType.NoCompanyByDetailsProvidedException);
		}
		}catch (Exception e) {
			if (e instanceof ProgramExceptions) {
				throw e;
			}
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		
		if (!(userLoginData.getUserType() .equals(UserType.Company))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
	}
	
	private void validGetCouponBetwinPrices (UserLoginData userLoginData, float min , float max) throws Exception	{
		if ((userLoginData.getUserType().name().equals((UserType.Company).name()))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		if(min<0) {
			throw new ProgramExceptions(ErrorType.IllegalPriceException);
		}
		if (max>99999) {
			throw new ProgramExceptions(ErrorType.IllegalPriceException);
		}
	}

	public Coupon getCouponByName( String couponName) {
		Coupon coupon= couponDao.findByName(couponName);
		return coupon;
	}

	
}
