package com.hai.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.dao.ProductDAOImpl;
import com.hai.idao.IProductDAO;
import com.hai.iservice.IProductService;
import com.hai.model.Product;
@SuppressWarnings("unchecked")
public class ProductServiceImpl implements IProductService {
	private IProductDAO productDAO;
	private SessionFactory sessionFactory;
	private Logger LOGGER;
	
	
	public ProductServiceImpl(SessionFactory sessionFactory) {
		productDAO = new ProductDAOImpl(sessionFactory);
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());
	}


	@Override
	public Integer getNumberOfProduct(int subCategoryID) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Integer result = null;
		try {
		String hql = "SELECT COUNT(*) FROM Product Where SubCategory_ID =" +subCategoryID;
		Query<Long> query = session.createQuery(hql);
		result = Integer.parseInt(query.getSingleResult().toString());
		session.getTransaction().commit();
		LOGGER.info("Got the number of product");
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.info("Can't get number of products");
		}
		finally {
			session.close();
			LOGGER.info("Close session");
		}
		return result;
		
	}
	
	@Override
	public List<Product> getAllProduct(){
		return productDAO.findAll();
	}


	
	@Override
	public List<Product> getProductByPage(int subCategoryID, int startPosition, int maxResult) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> listOfProducts = null;
		try {
			Query<Product> query = session.createQuery("From Product Where SubCategory_ID="+subCategoryID);
			query.setFirstResult(startPosition);
			query.setMaxResults(12);
			listOfProducts = query.getResultList();
			LOGGER.info("Got list of products by page");
		}
		catch(Exception e ) {
			e.printStackTrace();
			LOGGER.info("Can't getProduct by Page");
		}
		finally {
			session.close();
			LOGGER.info("Close session");
		}
		return listOfProducts;
	}


	
	
}
