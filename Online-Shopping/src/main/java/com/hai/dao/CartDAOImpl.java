package com.hai.dao;

import java.util.List;

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

	
	private Session session;
	
	public CartDAOImpl(SessionFactory sessionFactory) {
		session = sessionFactory.openSession();
	}

	
	public void save(Cart cart) {
		session.save(cart);
	}

	public void update(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Integer cartID) {
		// TODO Auto-generated method stub
		
	}

	public Cart findById(Integer cartID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cart> findAll() {
		String hsql = "from Cart";
		List<Cart> carts;
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Cart> query = session.createQuery(hsql);
		carts = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return carts;
	}

	public List<Cart> findByProperty(String name, Object proValue) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
