package com.hai.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.ICategoryService;
import com.hai.iservice.IProductService;
import com.hai.iservice.IUserService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Category;
import com.hai.model.Users;
import com.hai.model.Users.LoginStatus;
import com.hai.model.Wishlist;
import com.hai.service.CategoryServiceImpl;
import com.hai.service.ProductServiceImpl;
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
	private ICategoryService categoryService;
	private IProductService productService;
	private Logger LOGGER;

	public MyRequestListener() {

		;
		LOGGER = Logger.getLogger(MyRequestListener.class.getName());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		// Get sessionFactory and userService
		sessionFactory = (SessionFactory) sre.getServletContext().getAttribute("sessionFactory");
		userService = new UserServiceImpl(sessionFactory);
		categoryService = new CategoryServiceImpl(sessionFactory);
		productService = new ProductServiceImpl(sessionFactory);
		
		// Get request from ServletRequestEvent
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

		HttpSession session = request.getSession();
		Users sessionUser = (Users) session.getAttribute("user");
		Cart sessionCart = (Cart) session.getAttribute("cart");
		Wishlist sessionWishlist = (Wishlist) session.getAttribute("wishlist");
		Cookie[] cookies = request.getCookies();

		/*
		 * Handling remember login.
		 */
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
					// Check if wether exist
					if (user != null && user.getToken().equals(token)
							&& user.getLoginStatus().equals(LoginStatus.ACTIVE.toString())) {
						// If session doesnt have user yet, seve user to session
						session.setAttribute("user", user);
						LOGGER.info("Add user to session");

					} else {
						// If token is invalid , we should remove user from session
						if (sessionUser != null)
							session.removeAttribute("user");
					}

					break;
				} else if (sessionUser != null) {
					// If there is no cookie login ID , we should remove user from session
					session.removeAttribute("user");
				}

			}

		} else if (sessionUser != null) {
			// If there is no cookie , it mean no cookie logiID , remove session
			session.removeAttribute("user");
		}
		/*
		 * This code for add Category, cart, wishlist to session so from session we can
		 * pick it up easier. When there is somthing changed we will update this.
		 */

		// Category
		if (session.getAttribute("categories") == null) {
			List<Category> categories = categoryService.findAll();
			session.setAttribute("categories", categories);
			LOGGER.info("Add Categories in Session");
		}

		/*
		 * I'm going to divide into two case. 1. User not login yet and 2. User loingin
		 * already
		 * 
		 */

		if (sessionUser == null ) {
			// Set up cart
			if (sessionCart == null) {
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("cartCookie")) {
							Cart cart = new Cart();
							List<CartDetail> listOfItems = new ArrayList<>();

							// Cookies will have format: Product_id_Ammount:Product_id_Ammount:
							String cartCookie = cookie.getValue();
							String[] itemCookies = cartCookie.split(":");
							// Get list of cart Detail
							for (String itemCookie : itemCookies) {
								int productID = Integer.parseInt(itemCookie.split("_")[0]);
								int amount = Integer.parseInt(itemCookie.split("_")[1]);
								// Get item
								CartDetail cartDetail = new CartDetail();
								cartDetail.setAmount(amount);
								cartDetail.setCart(cart);
								cartDetail.setProduct(productService.getProductByID(productID));
								listOfItems.add(cartDetail);
							}
							

							cart.setCartDetails(listOfItems);
							session.setAttribute("cart", cart);
						}
					
					}
				}
			}
			// Set up wishlist
			if(sessionWishlist==null) {
				if(cookies != null) {
				
					for (Cookie cookie : cookies) {
						if(cookie.getName().equals("wishlistCookie")) {
							Wishlist wishlist = new  Wishlist();
							//Wishlist cookie will have format 
							
						}
					}
				}
			}
		} 
		else {

		}

	}

}
