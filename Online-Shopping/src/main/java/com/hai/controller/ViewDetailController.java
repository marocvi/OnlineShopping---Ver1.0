package com.hai.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.ICommentService;
import com.hai.iservice.IProductService;
import com.hai.iservice.ISubCategoryService;
import com.hai.model.Comments;
import com.hai.model.Price;
import com.hai.model.Product;
import com.hai.model.SubCategory;
import com.hai.service.CommentServiceImpl;
import com.hai.service.ProductServiceImpl;
import com.hai.service.SubCategoryServiceImpl;

/**
 * Servlet implementation class ViewDetailController
 */
public class ViewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private ICommentService commentService;
	private ISubCategoryService subCategoryService;
	private SessionFactory sessionFactory;


	@Override
	public void init() throws ServletException {
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		productService = new ProductServiceImpl(sessionFactory);
		commentService = new CommentServiceImpl(sessionFactory);
		subCategoryService = new SubCategoryServiceImpl(sessionFactory);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declaration
		int productID = 1;
		Product product = null;
		// Get action
			
		if (request.getParameter("product_id") != null) {
			productID = Integer.parseInt(request.getParameter("product_id")); 
			product = productService.getProductByID(productID);
			//increases view of product
			product.setView(product.getView()+1);
			productService.update(product);
		}
	
		// Get array of Pic
		List<String> listOfImages = Arrays.asList(product.getListOfImages().split(","));
		// Get array of size
		List<String> listOfSizes = Arrays.asList(product.getSize().split(","));
		// Get array of color
		List<String> listOfColors = Arrays.asList(product.getColor().split(","));
		// Get price of Product
		// Check wether price is valid
		Double unitPrice = 0.0;
		for (Price price : product.getPrices()) {
			if (price.getEndDate().getTime() > new Date().getTime()
					&& price.getStartDate().getTime() < new Date().getTime()) {
				unitPrice = price.getUnitPrice();
			}
		}
		// Get comment. Because we cant fetch simultaneously mutilple entity so we use
		// commentservice to get commetn.
		List<Comments> listOfComments = commentService.getListOfCommentsByProduct(productID);

		// Get List Of Produce with same subcategory with viweing product.
		List<Product> listOfProductsBySubCategory = productService
				.getListOfProductBySubCategory(product.getSubcategory().getId());

		// Get List Of SubCategory by category
		List<SubCategory> listOfSubCategoriesByCategory = subCategoryService
				.getListOfSubCategoriesByCategory(product.getSubcategory().getCategory().getId());

		// add listOfImages, listOfSizes, listOfColors to request
		request.setAttribute("listOfImages", listOfImages);
		request.setAttribute("listOfSizes", listOfSizes);
		request.setAttribute("listOfColors", listOfColors);
		request.setAttribute("price", unitPrice);
		request.setAttribute("listOfComments", listOfComments);
		request.setAttribute("listOfProducts", listOfProductsBySubCategory);
		request.setAttribute("listOfSubCategories", listOfSubCategoriesByCategory);
		request.setAttribute("today", new Date().getTime());

		// add product to request to show detail
		request.setAttribute("product", product);
		request.getRequestDispatcher("product_detail.jsp").forward(request, response);
		;

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

}
