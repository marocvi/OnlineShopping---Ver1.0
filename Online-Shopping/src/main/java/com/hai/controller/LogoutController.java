package com.hai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.IUserService;
import com.hai.model.Users;
import com.hai.service.UserServiceImpl;

/**
 * Delete user form cookies,session Delete cart, wishlist Direct to Hompage
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private IUserService userService;
	private Logger LOGGER;

	@Override
	public void init() {
		// Get sessonFactory from context
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		// initialize UserService
		userService = new UserServiceImpl(sessionFactory);
		// initialize CartService
		LOGGER = Logger.getLogger(RegisterController.class.getName());

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Delete loginID cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginID")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					String loginID = cookie.getValue();
					String email = loginID.split("_")[0];
					LOGGER.info("Delete the cookies LoginID sucessfully");
					// Delete token in DB
					if (session.getAttribute("user") != null) {
						Users user = userService.getUserByEmail(email);
						user.setToken("");
						userService.updateUser(user);
						LOGGER.info("Delete token of user in DB sucessfully");
					}
				}
			}
		}

		// Delete Cart and wishlist TODO
		LOGGER.info("Delete the cart, wishlist sucessflly");
		// Delete session
		if(request.getSession()!=null) {
			request.getSession().invalidate();
		}
		//Delete cartCookie
		Cookie cookie = new Cookie("cartCookie", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		

		// Redirect to home
		response.sendRedirect("home");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
