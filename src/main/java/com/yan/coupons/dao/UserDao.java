package com.yan.coupons.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yan.coupons.entity.User;

public interface UserDao extends CrudRepository<User, Long>{
	
	@Query ("select u from User u where u.name =?1")
	public User findByName(String name);
	
//	in validation need to get user from entity and can't use the regular function. 
	public User findById (long id);

}
