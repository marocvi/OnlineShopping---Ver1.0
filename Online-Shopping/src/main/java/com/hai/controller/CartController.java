package com.hai.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CartController
 */
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOGGER;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		LOGGER = Logger.getLogger(CartController.class);
	}
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch (action) {
		case "cart":
			LOGGER.info("Call add to cart");
			request.getRequestDispatcher("addToCart").forward(request, response);
			break;
		case "viewCart":
			LOGGER.info("Call view cart items");
			request.getRequestDispatcher("cart-list.jsp").forward(request, response);
			break;
		case "remove":
			LOGGER.info("Call remove cartDetail or cart item");
			request.getRequestDispatcher("removeFromCart").forward(request, response);
			break;
		case "subtract":
			LOGGER.info("Call subtract amount of item");
			request.getRequestDispatcher("subtractItemAmount").forward(request, response);
			break;
		case "plus":
			LOGGER.info("Call plus amount of item");
			request.getRequestDispatcher("plusItemAmount").forward(request, response);
			break;
		default:
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
