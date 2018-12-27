package com.hai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.IProductService;
import com.hai.iservice.IWishListService;
import com.hai.model.Users;
import com.hai.model.Wishlist;
import com.hai.service.ProductServiceImpl;
import com.hai.service.WishlistServiceImpl;

/**
 * Servlet implementation class AddToWishlist
 */
public class AddItemToWishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IWishListService wishlistService;
	private IProductService productService;


	public void init() throws ServletException {
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		wishlistService = new WishlistServiceImpl(sessionFactory);
		productService = new ProductServiceImpl(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int productID = Integer.parseInt(request.getParameter("product_id"));
		Users sessionUser = (Users) request.getSession().getAttribute("user");
		
		List<Wishlist> listOfWishlistItems = (List<Wishlist>) request.getSession().getAttribute("listOfWishlistItems");
		
		
		if(sessionUser!=null) {
			//add product to wishlist in DB
			updateWishlistTableInDB(productID, sessionUser, listOfWishlistItems,request);
			
		}
		else {
			//add product to wishlist in cookie.
			addListOfWishlistItemToCookie(response, productID, listOfWishlistItems,request);
			
			
		}
		response.sendRedirect("home");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void addListOfWishlistItemToCookie(HttpServletResponse response, int productID,
			List<Wishlist> listOfWishlistItems, HttpServletRequest request) {
		if(listOfWishlistItems==null) {
			listOfWishlistItems = new ArrayList<>();
			Wishlist wishlist = new Wishlist();
			wishlist.setProduct(productService.getProductByID(productID));
			listOfWishlistItems.add(wishlist);
			//Save list of wishlist item to Session
			request.getSession().setAttribute("listOfWishlistItems", listOfWishlistItems);
		}
		else {
			Wishlist wishlist = new Wishlist();
			wishlist.setProduct(productService.getProductByID(productID));
			listOfWishlistItems.add(wishlist);
		}
		//save to Cookie
		saveListOfWishlistItemToCookie(response, listOfWishlistItems);
	}

	private void saveListOfWishlistItemToCookie(HttpServletResponse response, List<Wishlist> listOfWishlistItems) {
		String wishlistCookieValue = "";
		for(Wishlist wishlist: listOfWishlistItems) {
			wishlistCookieValue += wishlist.getProduct().getId()+":";
		}
		wishlistCookieValue = wishlistCookieValue.substring(0, wishlistCookieValue.length() - 1);
		Cookie wishlistCookie = new Cookie("wishlistCookie",wishlistCookieValue ) ;
		//Set up value for this cookie 24 h.
		wishlistCookie.setMaxAge(24*60*60);
		response.addCookie(wishlistCookie);
	}

	private void updateWishlistTableInDB(int productID, Users sessionUser, List<Wishlist> listOfWishlistItems, HttpServletRequest request) {
		if(listOfWishlistItems==null||listOfWishlistItems.size()==0) {
			listOfWishlistItems = new ArrayList<>();
			Wishlist wishlist = new Wishlist();
			wishlist.setProduct(productService.getProductByID(productID));
			wishlist.setUser(sessionUser);
			listOfWishlistItems.add(wishlist);
			//Save list of Item to session
			request.getSession().setAttribute("listOfWishlistItems", listOfWishlistItems);
			//save to DB
			wishlistService.save(wishlist);
		}
		else {
			for (Wishlist tempWishlist : listOfWishlistItems) {
				if(tempWishlist.getProduct().getId()!=productID) {
					Wishlist wishlist = new Wishlist();
					wishlist.setProduct(productService.getProductByID(productID));
					wishlist.setUser(sessionUser);
					listOfWishlistItems.add(wishlist);
					//save to DB
					wishlistService.save(wishlist);
					break;
				}
			}
		}
	}

	
	

}
