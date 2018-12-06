package com.hai.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.IUserService;
import com.hai.model.Users;
import com.hai.model.Users.LoginStatus;
import com.hai.service.UserServiceImpl;
import com.hai.util.CookieUtil;
import com.hai.util.CookieUtilImpl;
import com.hai.util.EmailUtil;
import com.hai.util.EmailUtil.EmailPurpose;
import com.hai.util.EmailUtilImpl;
import com.hai.util.EncryptUtil;
import com.hai.util.EncryptUtilImpl;

/**
 * This servlet handling request relating to the specific account infomation
 * Servlet implementation class ProfileController
 */
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private IUserService userService;
	private EmailUtil emailUtil;
	private CookieUtil cookieUtil;
	private Logger LOGGER;

	public void init() {
		// Get sessonFactory from context
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		userService = new UserServiceImpl(sessionFactory);
		LOGGER = Logger.getLogger(RegisterController.class.getName());
		emailUtil = new EmailUtilImpl();
		cookieUtil = new CookieUtilImpl();

	}

	public ProfileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		Users user = (Users) session.getAttribute("user");
		if (user != null) {

			switch (action) {
			// If request for view like (profile, close account,..)
			case "view":
				// Check wether user login yet.
				// Get parameter from request, forward to jsp depending on parameter.
				String choice = request.getParameter("choice");
				request.getRequestDispatcher("/profile.jsp?choice=" + choice).forward(request, response);

				break;

			// If request for updating the profile
			case "updateProfile":
				LOGGER.info("Update profile");
				// Get update information from user
				Users requestUser = userService.getUserFromRequest(request);

				user.setFirstName(requestUser.getFirstName());
				user.setLastName(requestUser.getLastName());
				user.setPhone(requestUser.getPhone());
				user.setAddress(requestUser.getAddress());
				// Update new information of user in DB
				userService.updateUser(user);
				LOGGER.info("Update User in DB sucessfylly!");
				response.sendRedirect("profile.jsp?action=view&choice=profile");
				break;

			case "changePassword":

				HashMap<String, String> error = userService.validateChangePassword(request);

				// Check wether there are some error
				if (error.size() > 0) {
					request.setAttribute("error", error);
					// Add information from last submit
					request.getRequestDispatcher("/profile?action=view&choice=cpassword").forward(request, response);
				} else {

					// Encrypt new password before save in DB
					EncryptUtil encrypt = new EncryptUtilImpl();
					user.setPasswords(encrypt.encryptMD5(request.getParameter("newPassword")));
					// Update new password to DB
					userService.updateUser(user);
					// Send email to User to notify 
					emailUtil.setToEmail(user.getEmail()).setPurpose(EmailPurpose.CHANGE_PASS).send();
					// Redirect to change password page
					String msg = "Your password has sucessfully update.";
					response.sendRedirect("profile?action=view&choice=cpassword&message=" + msg);
				}

				break;
			case "closeAccount":
				// Change login status to Close
				user.setLoginStatus(LoginStatus.CLOSE.toString());
				userService.updateUser(user);
				// Send email to manager

				emailUtil.setFromEmail("marocvi89@gmail.com").setPurpose(EmailPurpose.CLOSE_ACCOUNT)
						.setToEmail(user.getEmail()).send();
				//Delete cookies
					cookieUtil.deleteCookie("loginID", request);
				response.sendRedirect("home");
				break;
			}
		} else
			response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
