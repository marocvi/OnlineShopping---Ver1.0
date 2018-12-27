package com.hai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger LOGGER;

	@Override
	public void init() throws ServletException {
		LOGGER = Logger.getLogger(ProductController.class);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
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

		String action = request.getParameter("action");

		switch (action) {
		case "view":
			LOGGER.info("Call view");
			request.getRequestDispatcher("viewProducts").forward(request, response);
			break;
		case "detail":
			LOGGER.info("Call detail");
			request.getRequestDispatcher("viewDetail").forward(request, response);
			break;
		case "comment":
			LOGGER.info("Call comment");
			request.getRequestDispatcher("comment").forward(request, response);
			break;
		case "cart":
			LOGGER.info("Call cart");
			request.getRequestDispatcher("cart").forward(request, response);
			
			break;
		case "wishlist":
			LOGGER.info("Call wishlist");
			request.getRequestDispatcher("wishlist").forward(request, response);;
			break;
		case "compare":
			
			break;
		default:
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
