package com.hai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.hai.iservice.ICartService;
import com.hai.iservice.ICommentService;
import com.hai.iservice.IProductService;
import com.hai.iservice.ISubCategoryService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Comments;
import com.hai.model.Price;
import com.hai.model.Product;
import com.hai.model.SubCategory;
import com.hai.model.Users;
import com.hai.service.CartServiceImpl;
import com.hai.service.CommentServiceImpl;
import com.hai.service.ProductServiceImpl;
import com.hai.service.SubCategoryServiceImpl;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private ICommentService commentService;
	private ISubCategoryService subCategoryService;
	private ICartService cartService;
	private SessionFactory sessionFactory;

	private Logger LOGGER;

	@Override
	public void init() throws ServletException {
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		productService = new ProductServiceImpl(sessionFactory);
		commentService = new CommentServiceImpl(sessionFactory);
		subCategoryService = new SubCategoryServiceImpl(sessionFactory);
		cartService = new CartServiceImpl();
		LOGGER = Logger.getLogger(ProductController.class);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set up request
		request.setCharacterEncoding("UTF-8");
		// Declaration
		int productID = 0;
		Product product = null;
		HttpSession session = null;
		// Get action
		String action = "";

		if (request.getParameter("product_id") != null) {
			productID = Integer.parseInt(request.getParameter("product_id"));
		}
		session = request.getSession();
		product = productService.getProductByID(productID);
		action = request.getParameter("action");

		switch (action) {
		case "view":
			LOGGER.info("Call view Product");
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
			break;
		case "detail":
			LOGGER.info("Call view product detail");
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

			break;
		case "comment":
			LOGGER.info("Call add comment");
			// Get productID, content of comment from request.
			int producID = Integer.parseInt(request.getParameter("product_id"));
			String content = request.getParameter("content");
			Users user = (Users) request.getSession().getAttribute("user");
			// Check if content is emty or user login yet?
			if (!content.isEmpty() && user != null) {
				// Update to DB
				Comments comment = new Comments();
				comment.setContent(content);
				comment.setCreateDate(new Date());
				comment.setProduct(productService.getProductByID(producID));
				comment.setUser(user);
				comment.setUserName(user.getFirstName());
				// Set up request
				commentService.saveComment(comment);
			} else {
				response.getWriter().write("Please enter your account and add cotent to your comment!");
			}
			break;
		case "cart":
			LOGGER.info("Call cart");
			Cart sessionCart = (Cart) session.getAttribute("cart");
			int amount = 0;
			if(request.getParameter("amount")=="")  amount = 1;
			else amount = Integer.parseInt(request.getParameter("amount"));

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
				listOfItems.add(cartDetail);
				sessionCart.setCartDetails(listOfItems);
			
			}
			else {
				// Check whether this product is already in this cart
				boolean check = false;
				for (CartDetail cartDetail : sessionCart.getCartDetails()) {
					if (product.getId() == cartDetail.getProduct().getId()) {
						cartDetail.setAmount(cartDetail.getAmount()+amount);
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
					sessionCart.getCartDetails().add(cartDetail);

				}
				

			}
			sessionCart.setMoneyTotal(cartService.getMoneyTotal(sessionCart.getCartDetails()));
			//Save to the cookies
			//save to session
			session.setAttribute("cart", sessionCart);
			LOGGER.info("Done cart");
			break;
		case "wishlist":

			break;
		case "compare":

			break;
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
