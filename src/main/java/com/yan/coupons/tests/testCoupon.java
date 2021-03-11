//package com.yan.coupons.tests;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import com.yan.coupons.beans.Coupon;
//import com.yan.coupons.beans.User;
//import com.yan.coupons.enums.Category;
//import com.yan.coupons.enums.UserType;
//import com.yan.coupons.logic.CouponsController;
//
//public class testCoupon {
//
//	public static void main(String[] args) {
//		CouponsController coupon= new CouponsController();
//		Coupon com1= new Coupon("home video", 3000.00f, null, (Calendar)new GregorianCalendar(2019, 11, 1), (Calendar)new GregorianCalendar(2020, 01, 01), Category.ELECTRICITY, 300, "alam/222334.co.il", 4);	
//		Coupon com2= new Coupon("digital weight", 59.90f, null, (Calendar)new GregorianCalendar(2020, 11, 1), (Calendar)new GregorianCalendar(2021, 0, 1), Category.FOOD, 250, "shoping.co.ol", 6);
//		Coupon com3= new Coupon("Executive chair", 259.90f, "office",(Calendar)new GregorianCalendar(2020, 11, 1) , (Calendar)new GregorianCalendar(2021,10, 1), Category.HOME, 20, null, 2);
//		Coupon com4= new Coupon("the dust", 50, null, (Calendar)new GregorianCalendar(2021, 4, 1), (Calendar)new GregorianCalendar(2020, 11, 10), Category.HOME, 1000, "ikea/316.org.il", 7);
////		Coupons com5= new Coupons(name, price, discription, startDate, endDate, category, amount, image, company_id)
//		User user= new User("the boss",	"1&only", null, UserType.Admin	);
//		User user2= new User("	windows",	"jelly",	6l, UserType.Company	);
//
//		
//		try {
////		coupon.createCouponController(com4, user2);
////		coupon.deleteCouponController(3, user2);
////		coupon.updateCoupon(9, com4, user2);
////		coupon.getCouponBetwinPricesController(user, 40, 600);
////		coupon.getCouponsByCategoryController(user, Category.ELECTRICITY);
////		coupon.getCouponsByCompanyIdController(user, 2);
////		coupon.getCouponByIdController(user, 8);
////		boolean ex= coupon.isCouponNameExist("baag");
////		System.out.println("bag is exist:"+ ex);
////		coupon.getAllCouponsController(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//}
