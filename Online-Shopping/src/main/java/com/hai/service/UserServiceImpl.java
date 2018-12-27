package com.hai.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;

import com.hai.dao.UserDAOImpl;
import com.hai.idao.IUserDAO;
import com.hai.iservice.IUserService;
import com.hai.model.Users;
import com.hai.model.Users.LoginStatus;
import com.hai.util.EncryptUtil;
import com.hai.util.EncryptUtilImpl;

public class UserServiceImpl implements IUserService {
	private IUserDAO userDAO;
	private EncryptUtil encryptUtil;

	public UserServiceImpl(SessionFactory sessionFactory) {
		userDAO = new UserDAOImpl(sessionFactory);
		encryptUtil = new EncryptUtilImpl();
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
	public HashMap<String, String> validateUserInfor(HttpServletRequest request) {
		// Get user from the request
		Users user = getUserFromRequest(request);

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
		if (user.getPasswords().equals("")) {
			error.put("passwords", "Please enter your passwords !");
		}

		// Authenticate user information for login

		return error;

	}

	@Override
	public Users verifyUser(String verifyID) {
		List<Users> listOfUser = userDAO.findByProperty("verifyID", verifyID);
		Users user = null;
		if (listOfUser != null && listOfUser.size() >= 1) {

			for (Users tempUser : listOfUser) {
				tempUser.setCreateDate(new Date());
				tempUser.setLoginStatus(LoginStatus.ACTIVE.toString());
				tempUser.setCreateDate(new Date());
				tempUser.setVerifyID("");
				userDAO.update(tempUser);
				user = tempUser;
				break;
			}
			
		}
		return user;

	}

	@Override
	public boolean authenticate(String email, String passwords) {
		Users user = null;
		user = userDAO.findByEmail(email);

		if (user == null) {
			return false;
		}
		if (!user.getLoginStatus().equals(LoginStatus.ACTIVE.toString())) {
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

	@Override
	public Users getUserFromRequest(HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Add user information to user
		Users user = new Users();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		user.setPasswords(password);
		return user;
	}

	@Override
	public HashMap<String, String> validateChangePassword(HttpServletRequest request) {
		HashMap<String, String> error = new HashMap<>();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		String password = request.getParameter("oldPassword");
		password = encryptUtil.encryptMD5(password);

		// Validate input
		if (request.getParameter("oldPassword").equals("")) {
			error.put("oldPassword", "Please enter your old password");
		}
		if (request.getParameter("newPassword").equals("")) {
			error.put("newPassword", "Please enter your new password");
		}
		if (request.getParameter("retypePassword").equals("")) {
			error.put("retypePassword", "Please re-enter password");
		}
		if (!request.getParameter("newPassword").equals(request.getParameter("retypePassword"))
				&& error.get("newPassword") == null & error.get("retypePassword") == null) {
			error.put("notMatch", "Your password you re-enter is not match");
		}

		if (!password.equals(user.getPasswords()) && !password.equals("d41d8cd98f00b204e9800998ecf8427e")
				&& error.get("newPassword") == null & error.get("retypePassword") == null) {
			error.put("incorrectPassword", "Your old password is incorrect. Please try again");
		}

		return error;

	}

	@Override
	public List<Users> getUserByProperty(String name, Object proValue) {
		return userDAO.findByProperty(name, proValue);
	}

}
