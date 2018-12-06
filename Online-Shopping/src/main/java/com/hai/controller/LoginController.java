package com.hai.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.ICartService;
import com.hai.iservice.IUserService;
import com.hai.iservice.IWishListService;
import com.hai.model.Cart;
import com.hai.model.Users;
import com.hai.model.Wishlist;
import com.hai.service.CartServiceImpl;
import com.hai.service.UserServiceImpl;
import com.hai.service.WishlistServiceImpl;
import com.hai.util.EncryptUtil;
import com.hai.util.EncryptUtilImpl;

/**
 * Check Infromation from input If sucess: Add cart and wishlist from session to
 * user Add user to session. Save loginID cookies to browser to let
 * RequestLisner pick it up for next session
 * 
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;
	private IUserService userService;
	private ICartService cartService;
	private IWishListService wishlistService;
	private Logger LOGGER;

	public void init() {
		// Get sessonFactory from context
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		// initialize UserService
		userService = new UserServiceImpl(sessionFactory);
		// initialize CartService
		cartService = new CartServiceImpl();
		// intialize WishListService
		wishlistService = new WishlistServiceImpl();
		LOGGER = Logger.getLogger(RegisterController.class.getName());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check If Information correct then go to homepage else back to login page
		HashMap<String, String> error = userService.validateUserInfor(request);
		Users user = userService.getUserFromRequest(request);
		// Encryp User's Information
		EncryptUtil encrypt = new EncryptUtilImpl();
		user.setPasswords(encrypt.encryptMD5(user.getPasswords()));
		if (!userService.authenticate(user.getEmail(), user.getPasswords()) && !user.getEmail().equals("")
				&& !user.getPasswords().equals("d41d8cd98f00b204e9800998ecf8427e")) {
			error.put("auth", "Your email or password enterd are wrong. Please try again!");
		}

		if (error.size() > 0) {
			// Set error back to page
			request.setAttribute("error", error);

			// Add user'imformation from last submit
			request.setAttribute("user", user);

			// Error: Go back to registry page
			LOGGER.info("There is error, Come back to Login Page");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			;
		} else {

			// Get user from DB
			user = userService.getUserByEmail(user.getEmail());
			// Create new token for user
			String token = UUID.randomUUID().toString();
			// Save token with user
			user.setToken(token);
			userService.updateUser(user);
			// Create loginID
			String loginID = user.getEmail() + "_" + token;
			// Save loginID to cookies for maintain next request.
			Cookie loginCookie = new Cookie("loginID", loginID);
			loginCookie.setMaxAge(60 * 24);
			response.addCookie(loginCookie);
			// Save user to sesssion as well
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(86400);

			/*
			 * Get cart and wishlist from session and add to User's cart and wishlist. This
			 * make sure no items missing from user'session when they login
			 */
			if (session.getAttribute("cart") != null) {
				Cart cart = (Cart) session.getAttribute("cart");
				cart.setUser(user);
				cartService.update(cart);
			}
			if (session.getAttribute("wishlist") != null) {
				Wishlist wishlist = (Wishlist) session.getAttribute("wishlist");
				wishlist.setUser(user);
				wishlistService.update(wishlist);
			}

			// Direct to homepage
			LOGGER.info("Sucessfully login");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}

}
