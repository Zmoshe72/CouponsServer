package com.yan.coupons.enums;

public enum ErrorType  {

	NameAlreadyExistException(601,"this name is taken", true),
	NotAllowedException(602,"your are not allowed to do this action",true),
	NameTypedException(603,"you didn't enter the name well",true),
	ShortNameException (604,"User name is too short",true),
	NameCanNotBeChangedException(605,"you canw-'t change your name",true),
	UserTypeCanNotBeChangedException(606,"you can't change user type",false),
	NoCompanyByDetailsProvidedException(607,"according the details you privided there is no such company", true),
	NoCouponByDetailsProvidedException(608,"according the details you privided there is no such company",true),
	IllegalPriceException(609,"you have entered illegal price",true),
	IllegalCategoryException(610,"you must enter category only: ATTRACTIONS, ELCCTRICITY, FOOD, GADGETS, HOME, TOOLS",true),
	ExpiredDateException(611,"the date of the coupon have been expired",true),
	AmountException(612,"you have entered wrong amount",true),
	IdDoesNotMatch(614,"id doesn't match",true),
	NotEnoughDetails(616,"you didn't entered enough details",true),
	CanGetOnlyOnceException(617,"you can purchase only once",true),
	NotLeftException(618,"the amount you ask is not available",true),
	LoginFailedException (619,"failed to login", true),
	GeneralErrorException (621, "there was an error please try later",false);
	
	
	
	private int problemNum;
	private String message;
	private boolean toPrint;
	
	private ErrorType(int problemNum, String message, boolean toPrint) {
		this.problemNum = problemNum;
		this.message = message;
		this.toPrint = toPrint;
	}
	
	private ErrorType() {
	}

	public int getProblemNum() {
		return problemNum;
	}

	public String getMassage() {
		return message;
	}

	public boolean isToPrint() {
		return toPrint;
	}
	
	
	
}
