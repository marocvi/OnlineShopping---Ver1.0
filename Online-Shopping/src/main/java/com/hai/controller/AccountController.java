package com.hai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeController
 * 
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
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
		// Get session
		HttpSession session = request.getSession();
		// Get action , if action is null navigate to homepage.
		String action = request.getParameter("action") != null ? request.getParameter("action") : "home";

		switch (action) {
		// If action is "login" navigate to login page
		case "login":
			if (session.getAttribute("user") != null) {
				response.sendRedirect("home");
			} else
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			break;
		// If action is "register" navigate to register page
		case "register":
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			break;
		case "verify":

			request.getRequestDispatcher("/verify").forward(request, response);
			break;
		case "logout":
			request.getRequestDispatcher("/logout").forward(request, response);
			break;

		case "profile":
			if (session.getAttribute("user") != null) {
				request.getRequestDispatcher("/profile.jsp?choice=profile").forward(request, response);
			}
			else
				response.sendRedirect("home");
			
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
