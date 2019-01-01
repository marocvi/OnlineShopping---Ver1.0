package com.hai.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.IProductService;
import com.hai.model.Product;
import com.hai.service.ProductServiceImpl;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productSerivce;
	
	
	@Override
		public void init() throws ServletException {
			SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
			productSerivce = new ProductServiceImpl(sessionFactory);
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String brand = request.getParameter("brand");
		if(brand==null||brand=="") {
			brand= "Nokia";
		}
		
		//Greatest Product
		List<Product> greatestProducts = productSerivce.getListOfGreatestProductsByBrand(brand);
		
		//Brand
		List<String > brands = productSerivce.getListOfProductBrand();
		
		// Newest product
		List<Product> newestProducts = productSerivce.getListOfNewestProductsByBrand(brand);
		
		
		//Binding information to request
		
		request.setAttribute("newestProducts", newestProducts);
		request.setAttribute("greatestProducts", greatestProducts);
		request.setAttribute("brands", brands);
		request.setAttribute("today", new Date().getTime());
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
