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
 * Servlet implementation class ViewController
 */
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private SessionFactory sessionFactory;


	@Override
	public void init() throws ServletException {
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		productService = new ProductServiceImpl(sessionFactory);

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get selected page and subcategory to get list of Products
		int selectedPage = Integer.parseInt(request.getParameter("page"));
		int subCategoryID = Integer.parseInt(request.getParameter("subcategoryid"));
		int maxResult = 12;
		// Get start position to get result in DB using selected page
		int startPosition = selectedPage * maxResult - maxResult;
		int numberOfProductBySubCategory = productService.getNumberOfProduct(subCategoryID);
		// Get number of page should display
		int numberOfPages = 0;
		if (numberOfProductBySubCategory % 12 != 0) {
			numberOfPages = numberOfProductBySubCategory / 12 + 1;
		} else
			numberOfPages = numberOfProductBySubCategory / 12;
		// Get list of Products
		List<Product> listOfProductByPage = productService.getProductByPage(subCategoryID, startPosition,
				maxResult);
		// Add information to request
		request.setAttribute("numberOfProducts", listOfProductByPage.size());
		request.setAttribute("subCategoryID", subCategoryID);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("listOfProducts", listOfProductByPage);
		request.setAttribute("today", new Date().getTime());

		request.getRequestDispatcher("products.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
