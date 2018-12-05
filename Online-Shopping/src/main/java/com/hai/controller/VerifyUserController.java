package com.hai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.IUserService;
import com.hai.service.UserServiceImpl;

/**
 * This controller for processing email verification
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public class VerifyUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private IUserService registryService;


	public void init() {
		// Get sessonFactory from context
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		registryService = new UserServiceImpl(sessionFactory);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get verifyID, looking for user with that ID and authorized that aacount
		String verifyID = request.getParameter("verifyID");
		
		//Looking users
		if(registryService.verifyUser(verifyID)) {
			response.sendRedirect("verified");
		}
		else {
			response.sendRedirect("verified?error=true");
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
