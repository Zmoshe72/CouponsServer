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
import org.springframework.web.bind.annotation.RestController;

import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.entity.Company;
import com.yan.coupons.logic.CompanyController;

@RestController
@RequestMapping("/company")
public class companyAPI {
	
	@Autowired
	CompanyController companyController;
	
	
	@PostMapping
	public long createCompany(@RequestBody Company company) throws Exception {
		return companyController.createCompany(company);
	}
	
	@PutMapping 
	public String updateCompany(  @RequestBody Company company , HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		companyController.updateCompany(userLoginData.getIdCompany(), company);
//		return getting message for phase 3
		return "update has succeeded";
	}
	
	@GetMapping("/{idCompany}")
	public Company getCompany (@PathVariable("idCompany") long idCompany) throws Exception {
		Company company=companyController.getCompany(idCompany);
		return company;
	}
	
	@GetMapping 
	public List<Company> getAllCompany (HttpServletRequest request) throws Exception  {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<Company> companies =  companyController.getAllCompanies(userLoginData); 
		return companies;
		}
	
	@DeleteMapping ("/{idCompany}")
	public String deleteCompany(@PathVariable("idCompany") long idCompany, HttpServletRequest request) throws Exception{
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		companyController.deleteCompany(idCompany, userLoginData);
//		return getting message for phase 3
		return "the company "+ idCompany+" has deltetd!";
	}
	
}
