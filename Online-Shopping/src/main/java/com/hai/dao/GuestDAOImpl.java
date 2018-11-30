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

import com.hai.idao.IGuestDAO;
import com.hai.model.Guest;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class GuestDAOImpl implements IGuestDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public GuestDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	public boolean save(Guest guest) {
		LOGGER.debug("Call save Guest");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save guest go here
			session.save(guest);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save Guest sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save Guest into DB");
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

	public boolean update(Guest guest) {
		LOGGER.debug("Call update Guest");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(guest);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update Guest sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update Guest in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	public boolean delete(Guest guest) {
		LOGGER.debug("Call delete Guest");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(guest);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Guest sucessfully in DB");
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
		
		LOGGER.debug("Call delete Guest");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from Guest where id = :guestID";
			Query<Guest> query = session.createQuery(hql);
			query.setParameter("guestID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete Guest sucessfully in DB");
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

	public Guest findById(Integer guestID) {
		LOGGER.debug("Call find Guest by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Guest guest = null;
		
		try {
			session.beginTransaction();
			//Code fetch guest go here
			guest = session.get(Guest.class, guestID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Guest sucessfully in DB");
			return guest;
			
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

	public List<Guest> findAll() {
		LOGGER.debug("Call find  all Guest ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<Guest> guests = null;
		
		try {
			session.beginTransaction();
			//Code fetch guest go here
			Query<Guest> query = session.createQuery("From Guest");
			guests = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all Guest sucessfully in DB");
			return guests;
			
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

	public List<Guest> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Guest fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Guest> guests = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Guest> criteriaQuery = builder.createQuery(Guest.class);
			Root<Guest> categoryRoot = criteriaQuery.from(Guest.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Guest> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			guests  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return guests;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch Guest in DB");
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
