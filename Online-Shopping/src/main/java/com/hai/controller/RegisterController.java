package com.hai.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.IUserService;
import com.hai.model.Users;
import com.hai.model.Users.LoginStatus;
import com.hai.service.UserServiceImpl;
import com.hai.util.EmailUtil;
import com.hai.util.EmailUtil.EmailPurpose;
import com.hai.util.EmailUtilImpl;
import com.hai.util.EncryptUtil;
import com.hai.util.EncryptUtilImpl;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private IUserService userService;
	private Logger LOGGER;

	public void init() {
		// Get sessonFactory from context
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		userService = new UserServiceImpl(sessionFactory);
		LOGGER = Logger.getLogger(RegisterController.class.getName());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check If Information correct then go to login else back to singn up page
		HashMap<String, String> error = userService.validateUserInfor(request);
		Users user= userService.getUserFromRequest(request);
		if (userService.checkExist(user.getEmail())) {
			error.put("emailExist", "Your email has already existed. Please try another email!");
		}
		
		if (error.size() > 0) {
			// Set error back to page
			request.setAttribute("error", error);

			// Add user'imformation from last submit
			request.setAttribute("user", user);

			// Error: Go back to registry page
			LOGGER.info("There is error, Come back to Registry");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			;
		} else {
			
			// Encryp User's Information
			EncryptUtil encrypt = new EncryptUtilImpl();
			user.setPasswords(encrypt.encryptMD5(user.getPasswords()));

			// Save pendingUser to DB and send email to verify user
			user.setLoginStatus(LoginStatus.PENDING.toString());
			user.setVerifyID(UUID.randomUUID().toString());
			userService.saveUser(user);
			// Send email
			EmailUtil emailUtil = new EmailUtilImpl();
			emailUtil.setToEmail(user.getEmail()).setFromEmail("marocvi89@gmail.com")
					.setPurpose(EmailPurpose.VERIFICATION).setVerifyID(user.getVerifyID()).send();
			
			// Sucess: Redirect to Login page to prevent double-submit
			response.sendRedirect("account?action=login");
			LOGGER.info("User sucessfully save to DB. Go to verification phase");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
