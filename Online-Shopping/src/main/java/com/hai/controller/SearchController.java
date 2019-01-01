package com.hai.controller;

import java.io.IOException;
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
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private SessionFactory sessionFactory;
    
	
	@Override
		public void init() throws ServletException {
			sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
			productService = new ProductServiceImpl(sessionFactory);
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("searchKey");
		List<Product> listProductsByKey = productService.getListOfProductsByKeyName(searchKey);
		String strJson = "{\"product\":[";
		for (Product product : listProductsByKey) {
			strJson += "{\"id\":\""+product.getId()+"\",\"name\":\"" + product.getName() + "\",\"image\":\"" + product.getProfileImage() + "\",\"price\":\""
					+ productService.getValidPrice(product) + "\"},";

		}
		//Remove "," at the end of strJson;
		if(listProductsByKey.size()>0) {
			strJson= strJson.substring(0,strJson.length()-1);
		}
		strJson +="]}";
		response.getWriter().print(strJson);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
