package com.hai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class WishListController
 */
public class WishListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOGGER ;
	
	@Override
		public void init() throws ServletException {
			LOGGER = Logger.getLogger(WishListController.class);
			
	}
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "view":
			LOGGER.info("Call view Wishlist");
			request.getRequestDispatcher("viewWishlist").forward(request, response);;
			break;
		case "wishlist":
			LOGGER.info("Call wishlist");
			request.getRequestDispatcher("addToWishlist").forward(request, response);	
			break;
		case "remove":
			LOGGER.info("Call remove Wishlist");
			request.getRequestDispatcher("removeFromWishlist").forward(request, response);
			
			break;
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
