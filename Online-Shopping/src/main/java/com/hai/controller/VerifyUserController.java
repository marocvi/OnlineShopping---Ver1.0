package com.hai.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.IRoleService;
import com.hai.iservice.IUserRoleService;
import com.hai.iservice.IUserService;
import com.hai.model.UserRole;
import com.hai.model.Users;
import com.hai.service.RoleServiceImpl;
import com.hai.service.UserRolServiceImpl;
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
	private IUserService userService;
	private IUserRoleService userRoleService;
	private IRoleService roleService;


	@Override
	public void init() {
		// Get sessonFactory from context
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		userService = new UserServiceImpl(sessionFactory);
		userRoleService = new UserRolServiceImpl(sessionFactory);
		roleService = new  RoleServiceImpl(sessionFactory);
		

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get verifyID, looking for user with that ID and authorized that aacount
		String verifyID = request.getParameter("verifyID");
		
		//Looking users
		Users user = userService.verifyUser(verifyID);
		if(user!=null) {
			//If we got user we set up role for it
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(roleService.findByRoleName("Normal"));
			userRole.setStartDate(new Date());
			//Save user Role
			userRoleService.save(userRole);
			response.sendRedirect("verified");
		}
		else {
			response.sendRedirect("verified?error=true");
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
