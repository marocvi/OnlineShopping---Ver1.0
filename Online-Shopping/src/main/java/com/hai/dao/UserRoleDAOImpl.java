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

import com.hai.idao.IUserRoleDAO;
import com.hai.model.UserRole;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class UserRoleDAOImpl implements IUserRoleDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public UserRoleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	@Override
	public boolean save(UserRole userRole) {
		LOGGER.debug("Call save UserRole");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save userRole go here
			session.persist(userRole);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save UserRole sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save UserRole into DB");
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
	public boolean update(UserRole userRole) {
		LOGGER.debug("Call update UserRole");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(userRole);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update UserRole sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update UserRole in DB");
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
	public boolean delete(UserRole userRole) {
		LOGGER.debug("Call delete UserRole");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(userRole);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete UserRole sucessfully in DB");
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
	public boolean delete(Integer userRoleID) {
		
		LOGGER.debug("Call delete UserRole");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from UserRole where id = :userRoleID";
			Query<UserRole> query = session.createQuery(hql);
			query.setParameter("userRoleID", userRoleID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete UserRole sucessfully in DB");
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
	public UserRole findById(Integer userRoleID) {
		LOGGER.debug("Call find UserRole by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		UserRole userRole = null;
		
		try {
			session.beginTransaction();
			//Code fetch userRole go here
			userRole = session.get(UserRole.class, userRoleID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch UserRole sucessfully in DB");
			return userRole;
			
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
	public List<UserRole> findAll() {
		LOGGER.debug("Call find  all UserRole ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<UserRole> userRoles = null;
		
		try {
			session.beginTransaction();
			//Code fetch userRole go here
			Query<UserRole> query = session.createQuery("From UserRole");
			userRoles = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all UserRole sucessfully in DB");
			return userRoles;
			
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
	public List<UserRole> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find UserRole fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<UserRole> userRoles = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserRole> criteriaQuery = builder.createQuery(UserRole.class);
			Root<UserRole> categoryRoot = criteriaQuery.from(UserRole.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<UserRole> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			userRoles  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return userRoles;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch UserRole in DB");
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
