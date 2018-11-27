package com.hai.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.idao.ICartDAO;
import com.hai.model.Cart;
/**
 * This class is implementation of {@link com.hai.idao.ICartDAO}. It uses Hibernate for interaction with database.
 * Session Factory comes from Application Context .<br>
 * <b>Note: </b> We open session per request.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public class CartDAOImpl implements ICartDAO {

	
	private SessionFactory sessionFactory;
	private Session session;
	
	//Inject SessionFactory instance
	public CartDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public void save(Cart cart) {
		session= sessionFactory.openSession();
		session.beginTransaction();
		session.save(cart);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Cart cart) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		//Code go here
		session.update(cart);
		session.getTransaction().commit();
		session.close();
		
	}

	public boolean delete(Cart cart) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Code go here
		try {
			session.delete(cart);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean delete(Integer cartID) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Code go here
		
		try {
			String hsql = "Delete from Cart where id = :cartID";
			@SuppressWarnings("unchecked")
			Query<Cart> query = session.createQuery(hsql);
			query.setParameter("cartID", cartID);
			query.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			session.close();
			return false;
		}
		session.getTransaction().commit();
		session.close();
		return true;
		
	}

	public Cart findById(Integer cartID) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Cart cart= session.get(Cart.class, cartID);
		session.getTransaction().commit();
		session.close();
		return cart;
	}

	public List<Cart> findAll() {
		session= sessionFactory.openSession();
		session.beginTransaction();
		String hsql = "from Cart";
		List<Cart> carts;
		@SuppressWarnings("unchecked")
		Query<Cart> query = session.createQuery(hsql);
		carts = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return carts;
	}

	
	public List<Cart> findByProperty(String name, Object proValue) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<Cart> carts;
		try {
		//Use criteria Query for complex query to get safe type
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Cart> criteriaQuery = criteriaBuilder.createQuery(Cart.class);
		Root<Cart> cartRoot = criteriaQuery.from(Cart.class);
		ParameterExpression<Object> paramProValue = criteriaBuilder.parameter(Object.class);
		criteriaQuery.select(cartRoot).where(criteriaBuilder.equal(cartRoot.get(name),paramProValue));
		
		//from creiteriaQuery create normal query and execute it
		Query<Cart> query = session.createQuery(criteriaQuery);
		query.setParameter(paramProValue, proValue);
		
		//Get list of Cart form query
		carts  = query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			session.close();
			carts = null;
		}
		
		return carts;
	}

	
	
	
}


















