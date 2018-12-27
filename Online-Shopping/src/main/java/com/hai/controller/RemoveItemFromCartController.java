package com.hai.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.ICartService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Users;
import com.hai.service.CartServiceImpl;

/**
 * Servlet implementation class RemoveItemFromCart
 */
public class RemoveItemFromCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private ICartService cartService;
	private Logger LOGGER;

	@Override
	public void init() throws ServletException {
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		cartService = new CartServiceImpl(sessionFactory);
		LOGGER = Logger.getLogger(RemoveItemFromCartController.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("product_id"));
		Cart sessionCart = (Cart) request.getSession().getAttribute("cart");
		Users sessionUser = (Users) request.getSession().getAttribute("user");
		// Change cart in DB
		if (sessionUser != null) {
			cartService.removeCartDetail(sessionCart, productID);
		}
		// Change cart in Cookie
		else {
			changeCartCookie(sessionCart, productID, response);
		}
		// Send information in xml back to front

		String xmlCart = "<Cart><NumberOfItems>" + sessionCart.getCartDetails().size() + "</NumberOfItems>"
				+ "<MoneyTotal>" + sessionCart.getMoneyTotal() + "</MoneyTotal></Cart>";
		response.setContentType("text/html");
		response.getWriter().println(xmlCart);
		LOGGER.info("Done remove cart in session and DB");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void removeCartDetail(Cart cart, int productID) {
		// Remvove item
		List<CartDetail> listOfItems = cart.getCartDetails();
		for (CartDetail cartDetail : listOfItems) {
			if (cartDetail.getProduct().getId() == productID) {
				listOfItems.remove(cartDetail);
				break;
			}
		}
		// Change Price
		cart.setMoneyTotal(cartService.getMoneyTotal(listOfItems));
	}

	private void changeCartCookie(Cart cart, int productID, HttpServletResponse response) {
		removeCartDetail(cart, productID);
		// Save cart to the cookies. This cookies will be call by requestListener
		String cookieValue = "";
		for (CartDetail cartDetail : cart.getCartDetails()) {
			cookieValue += cartDetail.getProduct().getId() + "_" + cartDetail.getAmount() + "_" + cartDetail.getColor()
					+ "_" + cartDetail.getSize() + ":";
		}
		if (cookieValue != "") {

			// Remove ':' at the end of cookie
			cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
		} else {
			// delete cookie;
			Cookie cookie = new Cookie("cartCookie", cookieValue);
			cookie.setMaxAge(0);
			response.addCookie(cookie);

		}
	}
}
