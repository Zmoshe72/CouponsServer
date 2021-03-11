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

import com.yan.coupons.dto.NeedToLogin;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.dto.UserSeeData;
import com.yan.coupons.entity.User;
import com.yan.coupons.logic.UserController;

@RestController
@RequestMapping("/user")
public class userAPI {
	
	@Autowired
	private UserController userController;
		
	@PostMapping
	public long createUser (@RequestBody User user) throws Exception {
		userController.createUser(user);
		return user.getId();
	}
	
	@PostMapping ("/login")
	public UserSeeData login (@RequestBody NeedToLogin needToLogin ) throws Exception {
			return userController.login(needToLogin);
	}
	
	
	@PutMapping("/{idUser}")
	public String updateUser (@PathVariable("idUser") long idUser, @RequestBody User user) throws Exception {
		userController.updateUser(idUser, user);
//		return getting message for phase 3
		return "update has succeeded";
		}
	
	@GetMapping("/{idUser}")
	public User getUser ( @PathVariable("idUser") long idUser, HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		User user = userController.getUser( userLoginData);
		return user;
	}
	
	@GetMapping 
	public List<User> getAllUsers (  HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		List<User> users = (List<User>) userController.getAllUser(userLoginData);
		return users;
	}
	
	@DeleteMapping ("/{idUser}")
	public String deleteUser ( @PathVariable("idUser") long userId,  HttpServletRequest request) throws Exception {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		userController.deleteUser(userId, userLoginData);
//		return getting message for phase 3
		return "the user has been deleted";
	}
	
	
	}
