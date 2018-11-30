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

import com.hai.idao.IWishlistDAO;
import com.hai.model.Wishlist;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class WishlistDAOImpl implements IWishlistDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public WishlistDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	public boolean save(Wishlist wishlist) {
		LOGGER.debug("Call save Wishlist");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save wishlist go here
			session.save(wishlist);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save Wishlist sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save Wishlist into DB");
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

	public boolean update(Wishlist wishlist) {
		LOGGER.debug("Call update Wishlist");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(wishlist);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update Wishlist sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update Wishlist in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	public boolean delete(Wishlist wishlist) {
		LOGGER.debug("Call delete Wishlist");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(wishlist);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Wishlist sucessfully in DB");
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

	public boolean delete(Integer subCartgoryID) {
		
		LOGGER.debug("Call delete Wishlist");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from Wishlist where id = :wishlistID";
			Query<Wishlist> query = session.createQuery(hql);
			query.setParameter("wishlistID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete Wishlist sucessfully in DB");
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

	public Wishlist findById(Integer wishlistID) {
		LOGGER.debug("Call find Wishlist by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Wishlist wishlist = null;
		
		try {
			session.beginTransaction();
			//Code fetch wishlist go here
			wishlist = session.get(Wishlist.class, wishlistID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Wishlist sucessfully in DB");
			return wishlist;
			
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

	public List<Wishlist> findAll() {
		LOGGER.debug("Call find  all Wishlist ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<Wishlist> wishlists = null;
		
		try {
			session.beginTransaction();
			//Code fetch wishlist go here
			Query<Wishlist> query = session.createQuery("From Wishlist");
			wishlists = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all Wishlist sucessfully in DB");
			return wishlists;
			
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

	public List<Wishlist> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Wishlist fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Wishlist> wishlists = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Wishlist> criteriaQuery = builder.createQuery(Wishlist.class);
			Root<Wishlist> categoryRoot = criteriaQuery.from(Wishlist.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Wishlist> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			wishlists  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return wishlists;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch Wishlist in DB");
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
