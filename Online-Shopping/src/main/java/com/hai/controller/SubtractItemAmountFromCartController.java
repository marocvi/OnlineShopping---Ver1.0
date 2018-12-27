package com.hai.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.ICartService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Users;
import com.hai.service.CartServiceImpl;

/**
 * Servlet implementation class SubtractAnItemFromCartController
 */
public class SubtractItemAmountFromCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICartService cartService;

	@Override
	public void init() throws ServletException {
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		cartService = new CartServiceImpl(sessionFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		int productID = Integer.parseInt(request.getParameter("product_id"));
		Cart sessionCart = (Cart) request.getSession().getAttribute("cart");
		Users user = (Users) request.getSession().getAttribute("user");
		updateSession(sessionCart, productID);
		amount -= 1;
		if (amount > 0) {
			if (user != null) {
				// Update to DB
				cartService.update(sessionCart);
				//Update sessionCart
				sessionCart=cartService.getCartById(sessionCart.getId());
				request.getSession().setAttribute("cart", sessionCart);

			} else {
				// update to Cookie
				changeCartCookie(sessionCart, productID,response);
			}
			//Send information back to front.
			String cartDetail ="<Amount>"+amount+"</Amount>";
			String xmlCart = "<Cart><NumberOfItems>"+sessionCart.getCartDetails().size()+"</NumberOfItems><MoneyTotal>"
					+sessionCart.getMoneyTotal()+"</MoneyTotal>"+cartDetail +"</Cart>";
			response.setContentType("text/html");
			response.getWriter().println(xmlCart);
			System.out.println(xmlCart);

		} else {
			// remove item.
			request.getRequestDispatcher("removeFromCart").include(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void updateSession(Cart sessionCart, int productID) {
		List<CartDetail> listOfItems = sessionCart.getCartDetails();
		for (CartDetail cartDetail : listOfItems) {
			if (cartDetail.getProduct().getId() == productID) {
				cartDetail.setAmount(cartDetail.getAmount() - 1);
				break;
			}
		}
		sessionCart.setMoneyTotal(cartService.getMoneyTotal(listOfItems));
	}

	private void changeCartCookie(Cart cart, int productID,HttpServletResponse response) {
		// Save cart to the cookies. This cookies will be call by requestListener
		String cookieValue = "";
		for (CartDetail cartDetail : cart.getCartDetails()) {
			cookieValue += cartDetail.getProduct().getId() + "_" + cartDetail.getAmount() + "_" + cartDetail.getColor()
					+ "_" + cartDetail.getSize() + ":";
		}
		// Remove ':' at the end of cookie
		if (cookieValue != "")
			cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
		Cookie cookie = new Cookie("cartCookie", cookieValue);
		cookie.setMaxAge(24 * 60 * 60 * 30);
		response.addCookie(cookie);

	}

}
