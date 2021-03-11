package com.yan.coupons.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.yan.coupons.dao.CouponDao;
import com.yan.coupons.entity.Coupon;
import com.yan.coupons.enums.ErrorType;
import com.yan.coupons.exception.ProgramExceptions;

@Configuration
@EnableScheduling
public class CheckExpired {
	
	@Autowired
	private CouponDao couponDao;
	
//	@PostConstruct
//	@Scheduled (cron = "0 * * * * *")
	@Scheduled (cron =  "0 0 0 * * *")
	public void dailyCheck() throws Exception {
		Calendar dateNow = Calendar.getInstance();
		List<Coupon> coupons = new ArrayList<Coupon>();
		try {
		coupons= couponDao.findCouponsExpired(dateNow);
		for (Coupon coupon : coupons) {
			couponDao.deleteById(coupon.getId());
		}
		}catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");

		}
}

	
}