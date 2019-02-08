package com.hai.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.ICartService;
import com.hai.iservice.ICategoryService;
import com.hai.iservice.IProductService;
import com.hai.iservice.IUserService;
import com.hai.iservice.IWishListService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Category;
import com.hai.model.Users;
import com.hai.model.Users.LoginStatus;
import com.hai.model.Wishlist;
import com.hai.service.CartServiceImpl;
import com.hai.service.CategoryServiceImpl;
import com.hai.service.ProductServiceImpl;
import com.hai.service.UserServiceImpl;
import com.hai.service.WishlistServiceImpl;

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
	private ICartService cartService;
	private IWishListService wishlistService;
	private Logger LOGGER;

	public MyRequestListener() {

		LOGGER = Logger.getLogger(MyRequestListener.class.getName());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		//Ignore static resource		
		
		// Get sessionFactory and userService
		sessionFactory = (SessionFactory) sre.getServletContext().getAttribute("sessionFactory");
		userService = new UserServiceImpl(sessionFactory);
		categoryService = new CategoryServiceImpl(sessionFactory);
		productService = new ProductServiceImpl(sessionFactory);
		cartService = new CartServiceImpl(sessionFactory);
		wishlistService = new WishlistServiceImpl(sessionFactory);
		

		// Get request from ServletRequestEvent
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

		HttpSession session = request.getSession();
		Users sessionUser = (Users) session.getAttribute("user");
		Cart sessionCart = (Cart) session.getAttribute("cart");
		List<Wishlist> listOfWishlistItems = (List<Wishlist>) session.getAttribute("listOfWishlistItems");
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
		 * When session has cart and wishlist already , ignore the codes below.
		 */
		
		if (session.getAttribute("user") == null && sessionCart == null && cookies != null) {
			// Load Cart from cookie
			loadCartFromCookie(session, cookies);
		}
		else if(session.getAttribute("user")!= null && sessionCart == null && cookies != null) {
			//Load cart from DB;
			loadCartFromDatabase(session);
			
			
		}
		// Set up wishlist
		if(session.getAttribute("user")==null& listOfWishlistItems==null&& cookies!=null) {
			//Load wishlist from cookie
			loadWishlistItemsFromCookie(session,cookies);
		}
		else if(session.getAttribute("user")!= null && listOfWishlistItems == null && cookies != null) {
			//Load Cookie from DB
			loadWishlistFromDatabase(session);
			
			
		}
	}

	
	
	
	
	private void loadWishlistFromDatabase(HttpSession session) {
		List<Wishlist> listOfWishlistItems = wishlistService
				.getWishlistItemsByUser((Users) session.getAttribute("user"));
		session.setAttribute("listOfWishlistItems", listOfWishlistItems);
	}

	private void loadCartFromDatabase(HttpSession session) {
			Users sessionUser = (Users)session.getAttribute("user");
			Cart cart = sessionUser.getCart();
			session.setAttribute("cart", cart);
			LOGGER.info("Add Cart to session from listener");
		
	}

	private void loadCartFromCookie(HttpSession session, Cookie[] cookies) {
		Cart sessionCart;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("cartCookie")) {
				sessionCart = new Cart();
				List<CartDetail> listOfItems = new ArrayList<>();

				// Cookies will have format: Productid_Ammount:Productid_Ammount:
				String cartCookie = cookie.getValue();
				String[] itemCookies = cartCookie.split(":");
				// Get list of cart Detail
				for (String itemCookie : itemCookies) {
					int productID = Integer.parseInt(itemCookie.split("_")[0]);
					int amount = Integer.parseInt(itemCookie.split("_")[1]);
					String color = itemCookie.split("_")[2];
					String size = itemCookie.split("_")[3];
					// Get item
					CartDetail cartDetail = new CartDetail();
					cartDetail.setAmount(amount);
					cartDetail.setCart(sessionCart);
					cartDetail.setProduct(productService.getProductByID(productID));
					cartDetail.setMoney(productService.getValidPrice(cartDetail.getProduct()));
					cartDetail.setColor(color);
					cartDetail.setSize(size);
					listOfItems.add(cartDetail);
				}
				sessionCart.setCartDetails(listOfItems);
				sessionCart.setMoneyTotal(cartService.getMoneyTotal(listOfItems));
				session.setAttribute("cart", sessionCart);
				LOGGER.info("Add Cart to session from listener");
			}

		}
	}
	private void loadWishlistItemsFromCookie(HttpSession session, Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("wishlistCookie")) {
				
				List<Wishlist> listOfWishlistItems = new ArrayList<>();

				// Cookies will have format: Productid :Productid :
				String wishlistCookieValue = cookie.getValue();
				String[] wishlistCookies = wishlistCookieValue.split(":");
				// Get list of cart Detail
				for (String productID : wishlistCookies) {
					Wishlist wishlist = new Wishlist();
					wishlist.setProduct(productService.getProductByID(Integer.parseInt(productID)));
					listOfWishlistItems.add(wishlist);
				}
				session.setAttribute("listOfWishlistItems",listOfWishlistItems);
				LOGGER.info("Add WishList to session from listener");
			}

		}
	}
	

}









