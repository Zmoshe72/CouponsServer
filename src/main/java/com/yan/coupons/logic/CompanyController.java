package com.yan.coupons.logic;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yan.coupons.dao.CompanyDao;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.entity.Company;
import com.yan.coupons.enums.ErrorType;
import com.yan.coupons.enums.UserType;
import com.yan.coupons.exception.ProgramExceptions;

@Controller
public class CompanyController {

	@Autowired
	private CompanyDao companyDao;

	public long createCompany(Company comp) throws Exception {
		validateCreateCompany(comp);
		Company company;
		try {
			company = companyDao.save(comp);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		long id = company.getId();
		return id;
	}

	public void updateCompany(long idCompany, Company company) throws Exception {
		validateUpdateCompany(idCompany,company);
		try {
			companyDao.save( company);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}

	public Company getCompany (long idCompany) throws Exception {
		try {
			if((companyDao.findById(idCompany)) == null) {
				throw new ProgramExceptions(ErrorType.NoCompanyByDetailsProvidedException);
			}
		}catch (Exception e) {
			if (e instanceof ProgramExceptions) {
				throw e;
			}
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}

		Company company = new Company();
		try {
			company=companyDao.findById(idCompany);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return company;
	}

	@SuppressWarnings("unchecked")
	public List<Company> getAllCompanies (UserLoginData userLoginData) throws Exception {
		if (!(userLoginData.getUserType().equals(UserType.Admin))){
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}

		List<Company> companies= new ArrayList<>();
		try {
			companies =  (List<Company>) companyDao.getAllCompanies();
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return companies;
	}

	public void deleteCompany(long idCompany, UserLoginData userLoginData) throws Exception {
		if (!(userLoginData.getUserType().equals(UserType.Admin))){
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}

		try {
			if((companyDao.findById(idCompany)) == null) {
				throw new ProgramExceptions(ErrorType.NoCompanyByDetailsProvidedException);
			}
			companyDao.deleteById(idCompany);
		}catch (Exception e) {
			if (e instanceof ProgramExceptions) {
				throw e;
			}
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}

	private void validateCreateCompany(Company comp) throws Exception {
		if (comp.getName() == null) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}
		if (comp.getName().isEmpty()) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}		
		if (comp.getName().length() <2) {
			throw new ProgramExceptions(ErrorType.ShortNameException);
		}
		Company company = new Company();
		try {
			company = this.companyDao.findByName(comp.getName());
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		if (company != null && company.getId() == comp.getId()) {
			throw new ProgramExceptions(ErrorType.NameAlreadyExistException);
		}
	}

	private void validateUpdateCompany(long idCompany,Company comp) throws Exception {
		validateCreateCompany(comp);
		try {
			if ((companyDao.findById(idCompany)) == null) {
				throw new ProgramExceptions(ErrorType.NoCompanyByDetailsProvidedException);
			}
		}catch (Exception e) {
			if (e instanceof ProgramExceptions) {
				throw e;
			}
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}
}
