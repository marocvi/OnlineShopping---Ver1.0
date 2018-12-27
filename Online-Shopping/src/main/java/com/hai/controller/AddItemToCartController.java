package com.hai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.ICartService;
import com.hai.iservice.IProductService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Product;
import com.hai.model.Users;
import com.hai.service.CartServiceImpl;
import com.hai.service.ProductServiceImpl;

/**
 * Servlet implementation class AddToCartController
 */
public class AddItemToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private ICartService cartService;
	private SessionFactory sessionFactory;

	private Logger LOGGER;

	@Override
	public void init() throws ServletException {
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		productService = new ProductServiceImpl(sessionFactory);
		cartService = new CartServiceImpl(sessionFactory);
		LOGGER = Logger.getLogger(ProductController.class);

	}

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart sessionCart = getSessionCart(request);
		Users sessionUser = (Users) request.getSession().getAttribute("user");
		// Get action

		if (sessionUser == null) {
			// Save cart to the cookies. This cookies will be call by requestListener
			String cookieValue = "";
			for (CartDetail cartDetail : sessionCart.getCartDetails()) {
				cookieValue += cartDetail.getProduct().getId() + "_" + cartDetail.getAmount() + "_"
						+ cartDetail.getColor() + "_" + cartDetail.getSize() + ":";
			}
			// Remove ':' at the end of cookie
			cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
			Cookie cookie = new Cookie("cartCookie", cookieValue);
			cookie.setMaxAge(24 * 60 * 60 * 30);
			response.addCookie(cookie);
		} else {
			// Save session cart to DB. This data will be load at MyRequestListener
			if (sessionCart.getId() == 0) {
				sessionCart.setUser(sessionUser);
				cartService.save(sessionCart);

			} else {
				cartService.update(sessionCart);
				sessionCart=cartService.getCartById(sessionCart.getId());
			}

		}
		// save to session
		request.getSession().setAttribute("cart", sessionCart);
		LOGGER.info("Done cart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public Cart getSessionCart(HttpServletRequest request) {
		// Declare
		int productID = 0;
		int amount = 0;
		String color = "";
		String size = "";
		Product product = null;
		HttpSession session = null;

		// Initilize
		color = request.getParameter("color");
		size = request.getParameter("size");
		if (request.getParameter("product_id") != null) {
			productID = Integer.parseInt(request.getParameter("product_id"));
			product = productService.getProductByID(productID);
		}
		session = request.getSession();
		Cart sessionCart = (Cart) session.getAttribute("cart");
		if (request.getParameter("amount") == "")
			amount = 1;
		else
			amount = Integer.parseInt(request.getParameter("amount"));

		if (sessionCart == null) {
			// Get cart
			sessionCart = new Cart();
			List<CartDetail> listOfItems = new ArrayList<>();

			// Get cartDetails
			CartDetail cartDetail = new CartDetail();
			cartDetail.setCart(sessionCart);
			cartDetail.setProduct(product);
			cartDetail.setAmount(amount);
			cartDetail.setMoney(productService.getValidPrice(product));
			cartDetail.setColor(color);
			cartDetail.setSize(size);
			listOfItems.add(cartDetail);
			sessionCart.setCartDetails(listOfItems);

		} else {
			// Check whether this product is already in this cart
			boolean check = false;
			for (CartDetail cartDetail : sessionCart.getCartDetails()) {
				if (product.getId() == cartDetail.getProduct().getId()) {
					cartDetail.setAmount(cartDetail.getAmount() + amount);
					check = true;
					break;
				}
				
			}

			if (!check) {
				// Get cartDetails
				CartDetail cartDetail = new CartDetail();
				cartDetail.setCart(sessionCart);
				cartDetail.setProduct(product);
				cartDetail.setAmount(amount);
				cartDetail.setMoney(productService.getValidPrice(product));
				cartDetail.setColor(color);
				cartDetail.setSize(size);
				sessionCart.getCartDetails().add(cartDetail);

			}

		}
		sessionCart.setMoneyTotal(cartService.getMoneyTotal(sessionCart.getCartDetails()));
		return sessionCart;
	}

}
