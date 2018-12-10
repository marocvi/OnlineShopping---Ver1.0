package com.hai.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.hai.dao.CategoryDAOImpl;
import com.hai.idao.ICategoryDAO;
import com.hai.iservice.ICategoryService;
import com.hai.model.Category;

public class CategoryServiceImpl implements ICategoryService  {
	private ICategoryDAO categoryDAO;
	private SessionFactory sessionFactory;
	 
	public CategoryServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		categoryDAO = new CategoryDAOImpl(sessionFactory);
	}
	
	
	@Override
	public List<Category> findAll() {
		
		return categoryDAO.findAll();
	}

}
