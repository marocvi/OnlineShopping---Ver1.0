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

import com.hai.idao.IUserDAO;
import com.hai.model.Users;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class UserDAOImpl implements IUserDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	public boolean save(Users user) {
		LOGGER.debug("Call save Users");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save user go here
			session.save(user);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save Users sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save Users into DB");
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

	public boolean update(Users user) {
		LOGGER.debug("Call update Users");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(user);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update Users sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update Users in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	public boolean delete(Users user) {
		LOGGER.debug("Call delete Users");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(user);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Users sucessfully in DB");
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
		
		LOGGER.debug("Call delete Users");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from Users where id = :userID";
			Query<Users> query = session.createQuery(hql);
			query.setParameter("userID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete Users sucessfully in DB");
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

	public Users findById(Integer userID) {
		LOGGER.debug("Call find Users by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Users user = null;
		
		try {
			session.beginTransaction();
			//Code fetch user go here
			user = session.get(Users.class, userID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Users sucessfully in DB");
			return user;
			
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

	public List<Users> findAll() {
		LOGGER.debug("Call find  all Users ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<Users> users = null;
		
		try {
			session.beginTransaction();
			//Code fetch user go here
			Query<Users> query = session.createQuery("From Users");
			users = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all Users sucessfully in DB");
			return users;
			
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

	public List<Users> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Users fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Users> users = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
			Root<Users> categoryRoot = criteriaQuery.from(Users.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Users> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			users  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return users;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch Users in DB");
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
