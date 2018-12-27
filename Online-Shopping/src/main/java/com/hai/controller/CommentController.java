package com.hai.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.hai.iservice.ICommentService;
import com.hai.iservice.IProductService;
import com.hai.model.Comments;
import com.hai.model.Users;
import com.hai.service.CommentServiceImpl;
import com.hai.service.ProductServiceImpl;

/**
 * Servlet implementation class CommentController
 */
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	private ICommentService commentService;
	private SessionFactory sessionFactory;


	@Override
	public void init() throws ServletException {
		sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		productService = new ProductServiceImpl(sessionFactory);
		commentService = new CommentServiceImpl(sessionFactory);

	}  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int producID = Integer.parseInt(request.getParameter("product_id"));
		// Get productID, content of comment from request.
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
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} else {
			response.getWriter().write("Please enter your account and add cotent to your comment!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
