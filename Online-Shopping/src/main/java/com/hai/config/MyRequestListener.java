package com.hai.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.IUserService;
import com.hai.model.Users;
import com.hai.service.UserServiceImpl;

/**
 * Every request come to server it wil check whether user login by looking
 * loginID cookies from request, If yes it will save user to session , if no
 * nothing hapen.
 * 
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public class MyRequestListener implements ServletRequestListener {

	private SessionFactory sessionFactory;
	private IUserService userService;
	private Logger LOGGER;

	public MyRequestListener() {
		LOGGER = Logger.getLogger(MyRequestListener.class.getName());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	public void requestInitialized(ServletRequestEvent sre) {
		// Get sessionFactory and userService
		sessionFactory = (SessionFactory) sre.getServletContext().getAttribute("sessionFactory");
		userService = new UserServiceImpl(sessionFactory);

		// Get request from ServletRequestEvent
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		HttpSession session = request.getSession();
		// Looking for cookie loginID
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginID")) {
					String loginID = cookie.getValue();
					// Get email and token from loginID
					LOGGER.info(loginID);
					String email = loginID.split("_")[0];
					String token = loginID.split("_")[1];

					// Get user from db and check whether token valid or invalid.
					Users user = userService.getUserByEmail(email);
					if (user != null && user.getToken().equals(token)) {
						// If session doesnt have user yet, seve user to session

						session.setAttribute("user", user);
						LOGGER.info("Add user to session");

					} else {
						if (session.getAttribute("user") != null)
							session.removeAttribute("user");
					}

					break;
				}
			}
		}
	}

}
