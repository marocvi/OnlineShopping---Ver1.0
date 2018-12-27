package com.hai.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.IWishListService;
import com.hai.model.Users;
import com.hai.model.Wishlist;
import com.hai.service.WishlistServiceImpl;



@SuppressWarnings("unchecked")
public class RemoveItemFromWishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IWishListService wishlistService;
	
	@Override
	public void init() throws ServletException {
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		wishlistService = new WishlistServiceImpl(sessionFactory);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("product_id"));
		Users user = (Users) request.getSession().getAttribute("user");
		//update session
		updateSession(request);
		if(user!=null) {
			//update in DB
			wishlistService.deleteByIdAndUser(productID,user);
		}
		else {
			//update cookies
			updateCookies(request,response);
			
		}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void updateSession(HttpServletRequest request) {
		List<Wishlist> listOfWishlistItems = (List<Wishlist>) request.getSession().getAttribute("listOfWishlistItems");
		int productID = Integer.parseInt(request.getParameter("product_id"));
		for (Wishlist wishlist : listOfWishlistItems) {
			if(wishlist.getProduct().getId()==productID) {
				listOfWishlistItems.remove(wishlist);
				break;
			}
		}
	}
	
	private void updateCookies(HttpServletRequest request,HttpServletResponse response) {
		List<Wishlist> listOfWishlistItems = (List<Wishlist>) request.getSession().getAttribute("listOfWishlistItems");
		String wishlistCookieValue = "";
		for(Wishlist wishlist: listOfWishlistItems) {
			wishlistCookieValue += wishlist.getProduct().getId()+":";
		}
		if(!wishlistCookieValue.equals(""))
			wishlistCookieValue = wishlistCookieValue.substring(0, wishlistCookieValue.length() - 1);
		Cookie wishlistCookie = new Cookie("wishlistCookie",wishlistCookieValue ) ;
		//Set up value for this cookie 24 h.
		wishlistCookie.setMaxAge(24*60*60);
		response.addCookie(wishlistCookie);
	}

}
