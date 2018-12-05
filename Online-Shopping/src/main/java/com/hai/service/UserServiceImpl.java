package com.hai.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;

import com.hai.dao.UserDAOImpl;
import com.hai.idao.IUserDAO;
import com.hai.iservice.IUserService;
import com.hai.model.Users;
import com.hai.model.Users.LoginStatus;

public class UserServiceImpl implements IUserService {
	private IUserDAO userDAO;

	public UserServiceImpl(SessionFactory sessionFactory) {
		userDAO = new UserDAOImpl(sessionFactory);
	}

	@Override
	public boolean checkExist(String email) {

		Users user = userDAO.findByEmail(email);
		if (user != null) {
			return true;
		} else
			return false;

	}

	@Override
	public void saveUser(Users user) {
		userDAO.save(user);

	}

	@Override
	public HashMap<String, String> validateUserInfor(Users user) {
		// Hashmap to store number of erors may ever occur.
		HashMap<String, String> error = new HashMap<>();

		// Validation User's Information
		// Validate email
		if (user.getEmail() == "") {
			error.put("emailNull", "Please enter your email!");
		}

		// Validate first name
		if (user.getFirstName() == "") {
			error.put("firstName", "Plese enter your first name");
		}

		// Validate last name
		if (user.getLastName() == "") {
			error.put("lastName", "Plese enter your last name");
		}

		// Validate phone
		if (user.getPhone() == "") {
			error.put("phone", "Please enter your phone numbers");
		}

		// Validate address
		if (user.getAddress() == "") {
			error.put("address", "Please enter your address");
		}

		// Validate password, the password has value "" will be encrypted to
		// "d41d8cd98f00b204e9800998ecf8427e" using MD5
		if (user.getPasswords().equals("d41d8cd98f00b204e9800998ecf8427e")) {
			error.put("passwords", "Please enter your passwords !");
		}
		return error;

	}

	@Override
	public boolean verifyUser(String verifyID) {
		List<Users> listOfUser = userDAO.findByProperty("verifyID", verifyID);
		if (listOfUser!=null&&listOfUser.size() >= 1 ) {

			for (Users user : listOfUser) {
				user.setCreateDate(new Date());
				user.setLoginStatus(LoginStatus.ACTIVE.toString());
				user.setVerifyID("");
				userDAO.update(user);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean authenticate(String email, String passwords) {
		Users user = null;
		user = userDAO.findByEmail(email);
		
		if (user == null) {
			return false;
		}
		if(!user.getLoginStatus().equals(LoginStatus.ACTIVE.toString())) {
			return false;
		}
		if (!passwords.equals(user.getPasswords())) {
			return false;
		}
		// Check login status

		return true;
	}

	@Override
	public Users getUserByEmail(String email) {

		return userDAO.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		userDAO.update(user);
		
	}

}
