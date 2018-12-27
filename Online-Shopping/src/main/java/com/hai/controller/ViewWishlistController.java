package com.hai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hai.model.Product;
import com.hai.model.Wishlist;

/**
 * Servlet implementation class ViewWishlistController
 */
public class ViewWishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Wishlist> listOfWishlistItems = (List<Wishlist>) request.getSession().getAttribute("listOfWishlistItems");
		
		// Get selected page and subcategory to get list of Products
		int selectedPage = Integer.parseInt(request.getParameter("page"));
		int maxResult = 12;
		// Get start position to get result in DB using selected page
		int startPosition = selectedPage * maxResult - maxResult;
		int numberOfProductsInWishlist =0;
		if(listOfWishlistItems!=null) {
			numberOfProductsInWishlist=listOfWishlistItems.size();
		}
		// Get number of page should display
		int numberOfPages = 0;
		if (numberOfProductsInWishlist % 12 != 0) {
			numberOfPages = numberOfProductsInWishlist / 12 + 1;
		} else
			numberOfPages = numberOfProductsInWishlist / 12;
		// Get list of Products
		List<Product> listOfProductsByPage = getProductByPage(startPosition, maxResult, request);
		// Add information to request
		request.setAttribute("numberOfProducts", listOfProductsByPage.size());
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("listOfProducts", listOfProductsByPage);
		request.setAttribute("today", new Date().getTime());

		request.getRequestDispatcher("wishlist.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@SuppressWarnings("unchecked")
	private List<Product> getProductByPage(int startPosition, int maxResult, HttpServletRequest request){
		List<Wishlist> listOfWishlistItems = (List<Wishlist>) request.getSession().getAttribute("listOfWishlistItems");
		List<Product> products = new ArrayList<>();
		if(listOfWishlistItems==null||listOfWishlistItems.size()==0) {
			return products;
		}
		for(int i = startPosition ;i<startPosition+maxResult;i++) {
			Product product = listOfWishlistItems.get(i).getProduct();
			products.add(product);
			if(i==listOfWishlistItems.size()-1) {
				break;
			}
		}
		return products;
	}

}
