package com.hai.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.dao.ProductDAOImpl;
import com.hai.idao.IProductDAO;
import com.hai.iservice.IProductService;
import com.hai.model.Price;
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
			String hql = "SELECT COUNT(*) FROM Product Where SubCategory_ID =" + subCategoryID;
			Query<Long> query = session.createQuery(hql);
			result = Integer.parseInt(query.getSingleResult().toString());
			session.getTransaction().commit();
			LOGGER.info("Got the number of product");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Can't get number of products");
		} finally {
			session.close();
			LOGGER.info("Close session");
		}
		return result;

	}

	@Override
	public List<Product> getAllProduct() {
		return productDAO.findAll();
	}

	@Override
	public List<Product> getProductByPage(int subCategoryID, int startPosition, int maxResult) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> listOfProducts = null;
		try {
			Query<Product> query = session.createQuery("From Product Where SubCategory_ID=" + subCategoryID);
			query.setFirstResult(startPosition);
			query.setMaxResults(12);
			listOfProducts = query.getResultList();
			LOGGER.info("Got list of products by page");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Can't getProduct by Page");
		} finally {
			session.close();
			LOGGER.info("Close session");
		}
		return listOfProducts;
	}

	@Override
	public Product getProductByID(int productID) {

		return productDAO.findById(productID);
	}

	@Override
	public List<Product> getListOfProductBySubCategory(int subCategoryID) {

		Session session = sessionFactory.openSession();
		List<Product> listOfProductsBySubCategory = null;
		try {
			session.beginTransaction();
			String hql = "from Product where SubCategory_ID=:subCategoryID";
			Query<Product> query = session.createQuery(hql);
			query.setParameter("subCategoryID", subCategoryID);
			query.setFirstResult(0);
			query.setMaxResults(5);
			listOfProductsBySubCategory = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			LOGGER.info("Close session");
		}

		return listOfProductsBySubCategory;
	}

	@Override
	public  double getValidPrice(Product product) {
		List<Price> prices = product.getPrices();
		for (Price price : prices) {
			if (price.getStartDate().getTime() <= new Date().getTime()
					&& price.getEndDate().getTime() >= new Date().getTime()) {
				NumberFormat formatter = new DecimalFormat(".00");
				return Double.parseDouble( formatter.format(price.getUnitPrice()));
			}
		}
		return 0.0;
	}

	@Override
	public void update(Product product) {
		productDAO.update(product);
	}

	@Override
	public List<Product> getListOfGreatestProductsByBrand(String brand) {
		Session session = sessionFactory.openSession();
		List<Product> products = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);
			ParameterExpression<String> paramter = builder.parameter(String.class);
			criteriaQuery.select(root);
			criteriaQuery.where(builder.equal(root.get("brand"), paramter));
			criteriaQuery.orderBy(builder.desc(root.get("view")));
			Query<Product> query = session.createQuery(criteriaQuery);
			query.setParameter(paramter, brand);
			query.setFirstResult(0);
			query.setMaxResults(4);
			products = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Can't get list of greatest products by brand");
		} finally {
			session.close();
			LOGGER.info("Session close");

		}
		return products;
	}

	@Override
	public List<Product> getListOfNewestProductsByBrand(String brand) {
		Session session = sessionFactory.openSession();
		List<Product> products = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);
			ParameterExpression<String> paramter = builder.parameter(String.class);
			criteriaQuery.select(root);
			criteriaQuery.where(builder.equal(root.get("brand"), paramter));
			criteriaQuery.orderBy(builder.asc(root.get("importDate")));
			Query<Product> query = session.createQuery(criteriaQuery);
			query.setParameter(paramter, brand);
			query.setFirstResult(0);
			query.setMaxResults(4);
			products = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Can't get list of greatest products by brand");
		} finally {
			session.close();
			LOGGER.info("Session close");

		}
		return products;
	
	}

	@Override
	public List<String> getListOfProductBrand() {
		String sql = "Select distinct brand from Product";
		List<String > brands = new ArrayList<>();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query<String> query = session.createNativeQuery(sql);
			brands = query.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("Can't get brands");
		}
		finally {
			session.close();
		}
		return brands;
	}

	@Override
	public List<Product> getListOfProductsByKeyName(String keyName) {
		String hql="from Product where name like '%"+keyName+"%'";
		Session session = sessionFactory.openSession();
		List<Product> products = new ArrayList<>();
		
		try {
			session.beginTransaction();
			//Code go here
			Query<Product> query = session.createQuery(hql);
			products = query.getResultList();
			
			
		}
		catch(Exception e) {
			LOGGER.error("Can't get List of Product from DB");
			e.printStackTrace();
		}
		finally {
			session.close();
			LOGGER.info("Close session");
		}
		return products;
	}
	

}
