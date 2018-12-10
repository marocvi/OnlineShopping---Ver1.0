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

import com.hai.idao.ISupplierDAO;
import com.hai.model.Supplier;
/**
 * This class provide implementation of {@link com.hai.idao.ISupplierDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class SupplierDAOImpl implements ISupplierDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public SupplierDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	@Override
	public boolean save(Supplier supplier) {
		LOGGER.debug("Call save Supplier");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save supplier go here
			session.save(supplier);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save Supplier sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save Supplier into DB");
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
	public boolean update(Supplier supplier) {
		LOGGER.debug("Call update Supplier");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(supplier);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update Supplier sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update Supplier in DB");
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
	public boolean delete(Supplier supplier) {
		LOGGER.debug("Call delete Supplier");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(supplier);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Supplier sucessfully in DB");
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
		
		LOGGER.debug("Call delete Supplier");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from Supplier where id = :supplierID";
			Query<Supplier> query = session.createQuery(hql);
			query.setParameter("supplierID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete Supplier sucessfully in DB");
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
	public Supplier findById(Integer supplierID) {
		LOGGER.debug("Call find Supplier by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Supplier supplier = null;
		
		try {
			session.beginTransaction();
			//Code fetch supplier go here
			supplier = session.get(Supplier.class, supplierID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Supplier sucessfully in DB");
			return supplier;
			
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
	public List<Supplier> findAll() {
		LOGGER.debug("Call find  all Supplier ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<Supplier> suppliers = null;
		
		try {
			session.beginTransaction();
			//Code fetch supplier go here
			Query<Supplier> query = session.createQuery("From Supplier");
			suppliers = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all Supplier sucessfully in DB");
			return suppliers;
			
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
	public List<Supplier> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Supplier fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Supplier> suppliers = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Supplier> criteriaQuery = builder.createQuery(Supplier.class);
			Root<Supplier> categoryRoot = criteriaQuery.from(Supplier.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Supplier> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			suppliers  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return suppliers;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch Supplier in DB");
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
