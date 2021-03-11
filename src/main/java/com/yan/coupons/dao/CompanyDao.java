package com.yan.coupons.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yan.coupons.entity.Company;

@Repository
public interface CompanyDao  extends CrudRepository<Company, Long> {
	
	public Company findByName(String name);
	
	public Company findById (long id);
	
	@Query ("select c from Company c")
	public Company getAllCompanies();

	public boolean existsCompanyById(long id);
	
	
}
