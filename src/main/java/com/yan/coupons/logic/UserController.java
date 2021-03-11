package com.yan.coupons.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yan.coupons.entity.User;
import com.yan.coupons.dao.UserDao;
import com.yan.coupons.dto.NeedToLogin;
import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.dto.UserSeeData;
import com.yan.coupons.enums.ErrorType;
import com.yan.coupons.enums.UserType;
import com.yan.coupons.exception.ProgramExceptions;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CacheController cacheController;

	final String abraKadaba = "!)@$yaniv^%^!";

	public long createUser(User user) throws Exception {
		validateCreateUser(user);
//		there is different check for create and update so it's not include in validate create user.
		if (this.userDao.findByName(user.getname()) != null) {
			throw new ProgramExceptions(ErrorType.NameAlreadyExistException);
		}
		User temp = new User();
		try {
		temp = this.userDao.save(user);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return temp.getId();
	}

	public void updateUser(long idUser, User user) throws Exception {
		validateUpdateUser(idUser, user);
		try {
		userDao.save(user);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
	}

	public User getUser( UserLoginData userLoginData) throws Exception {
		long idUser= userLoginData.getIdUser();
//		if (!(userLoginData.getUserType().equals(UserType.Admin))) {
//			throw new ProgramExceptions(ErrorType.NotAllowedException);
//		}
		User user = new User();
		try {
		user = userDao.findById(idUser);
		} catch (Exception e) {
			throw new ProgramExceptions(e, ErrorType.GeneralErrorException, "there was an error please try later");
		}
		return user;
	}

	public Iterable<User> getAllUser(UserLoginData user) throws Exception {
		if (!(user.getUserType().equals(UserType.Admin))) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
		return userDao.findAll();
	}

	public void deleteUser(long idUser, UserLoginData userLoginData) throws Exception {
		if (!(userLoginData.getUserType() == UserType.Admin)) {
			throw new ProgramExceptions(ErrorType.NotAllowedException);
		}
//		List<Purchase> purchasesByUser= new ArrayList();
//		purchasesByUser= purchaseDao.findByUserId(idUser);
//		for (Purchase purchases : purchasesByUser) {
//			purchsDao.deletePurches(purchases.getIdUser());
//		}
		userDao.deleteById(idUser);
	}

	private void validateCreateUser(User user) throws Exception {
		if (user.getname() == null) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}

		if (user.getname().isEmpty()) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}

		if (user.getname().length() < 2) {
			throw new ProgramExceptions(ErrorType.ShortNameException);
		}

	}

	private void validateUpdateUser(long idUser, User user) throws Exception {
		validateCreateUser(user);
		// if there is same name need to check if it's not the same user.
		String oldName = userDao.findById(idUser).getname();
		if (!(oldName.equals(user.getname()))) {
			throw new ProgramExceptions(ErrorType.NameCanNotBeChangedException);
		}
		User userByName = this.userDao.findByName(user.getname());
		if (userByName.getId() > 0 && userByName.getId() != idUser) {
			throw new ProgramExceptions(ErrorType.NameAlreadyExistException);
		}
		if (!(userDao.findById(idUser).getUserType().equals(user.getUserType()))) {
			throw new ProgramExceptions(ErrorType.UserTypeCanNotBeChangedException);
		}
	}

	public UserSeeData login(NeedToLogin needToLogin) throws ProgramExceptions {
		User user = userDao.findByName(needToLogin.getUserName());
		if (user == null) {
			throw new ProgramExceptions(ErrorType.NameTypedException);
		}
		if (!(user.getPassword().equals(needToLogin.getPassword()))) {
			throw new ProgramExceptions(ErrorType.LoginFailedException);
		}
		UserLoginData userLoginData = new UserLoginData();
		if (!(user.getUserType().equals(UserType.Company))) {
			userLoginData = new UserLoginData(user.getId(), null, user.getUserType());
		} else {
			userLoginData = new UserLoginData(user.getId(), user.getCompany().getId(), user.getUserType());
		}
		String token = generateToken(needToLogin);
		cacheController.putCacheController(token, userLoginData);
		UserSeeData userSeeData = new UserSeeData(token, userLoginData.getUserType());
		return userSeeData;
	}

	private String generateToken(NeedToLogin needToLogin) {
		int token = (needToLogin.getPassword() + abraKadaba + System.currentTimeMillis()).hashCode();
		return String.valueOf(token);
	}
}
