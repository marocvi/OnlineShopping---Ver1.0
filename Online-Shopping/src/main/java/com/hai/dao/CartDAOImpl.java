package com.hai.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.idao.ICartDAO;
import com.hai.model.Cart;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class CartDAOImpl implements ICartDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public CartDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	@Override
	public boolean save(Cart cart) {
		LOGGER.debug("Call save Cart");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save cart go here
			session.save(cart);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save Cart sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save Cart into DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			//Close session
			session.close();
		}
		
	}

	@Override
	public boolean update(Cart cart) {
		LOGGER.debug("Call update Cart");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(cart);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update Cart sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update Cart in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			session.close();
		}
	}
	

	@Override
	public boolean delete(Cart cart) {
		LOGGER.debug("Call delete Cart");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(cart);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Cart sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't delete Subcategory in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			//Close session
			session.close();
		}
	}

	@Override
	public boolean delete(Integer subCartgoryID) {
		
		LOGGER.debug("Call delete Cart");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from Cart where id = :cartID";
			Query<Cart> query = session.createQuery(hql);
			query.setParameter("cartID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete Cart sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't delete Subcategory in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			//Close session
			session.close();
		}
	}

	@Override
	public Cart findById(Integer cartID) {
		LOGGER.debug("Call find Cart by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Cart cart = null;
		
		try {
			session.beginTransaction();
			//Code fetch cart go here
			cart = session.get(Cart.class, cartID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Cart sucessfully in DB");
			return cart;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't fetch Subcategory in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return null;
		}
		finally {
			//Close session
			session.close();
		}
	}

	@Override
	public List<Cart> findAll() {
		LOGGER.debug("Call find  all Cart ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<Cart> carts = null;
		
		try {
			session.beginTransaction();
			//Code fetch cart go here
			Query<Cart> query = session.createQuery("From Cart");
			carts = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all Cart sucessfully in DB");
			return carts;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't fetch Subcategory in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return null;
		}
		finally {
			//Close session
			session.close();
		}
	}

	@Override
	public List<Cart> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Cart fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Cart> carts = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Cart> criteriaQuery = builder.createQuery(Cart.class);
			Root<Cart> categoryRoot = criteriaQuery.from(Cart.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Cart> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			carts  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return carts;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch Cart in DB");
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
			
			
		}
		finally {
			//Close session
			session.close();
		}
		
	}

}
