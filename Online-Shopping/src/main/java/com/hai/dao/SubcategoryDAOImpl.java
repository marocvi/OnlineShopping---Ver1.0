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

import com.hai.idao.ISubcategoryDAO;
import com.hai.model.SubCategory;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class SubcategoryDAOImpl implements ISubcategoryDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public SubcategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	@Override
	public boolean save(SubCategory subCategory) {
		LOGGER.debug("Call save SubCategory");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save subCategory go here
			session.save(subCategory);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save SubCategory sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save SubCategory into DB");
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
	public boolean update(SubCategory subCategory) {
		LOGGER.debug("Call update SubCategory");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(subCategory);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update SubCategory sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update SubCategory in DB");
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
	public boolean delete(SubCategory subCateogry) {
		LOGGER.debug("Call delete SubCategory");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(subCateogry);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete SubCategory sucessfully in DB");
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
		
		LOGGER.debug("Call delete SubCategory");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from SubCategory where id = :subCategoryID";
			Query<SubCategory> query = session.createQuery(hql);
			query.setParameter("subCategoryID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete SubCategory sucessfully in DB");
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
	public SubCategory findById(Integer subCategoryID) {
		LOGGER.debug("Call find SubCategory by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		SubCategory subCategory = null;
		
		try {
			session.beginTransaction();
			//Code fetch subCategory go here
			subCategory = session.get(SubCategory.class, subCategoryID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch SubCategory sucessfully in DB");
			return subCategory;
			
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
	public List<SubCategory> findAll() {
		LOGGER.debug("Call find  all SubCategory ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<SubCategory> subCategories = null;
		
		try {
			session.beginTransaction();
			//Code fetch subCategory go here
			Query<SubCategory> query = session.createQuery("From SubCategory");
			subCategories = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all SubCategory sucessfully in DB");
			return subCategories;
			
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
	public List<SubCategory> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find SubCategory fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<SubCategory> categories = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<SubCategory> criteriaQuery = builder.createQuery(SubCategory.class);
			Root<SubCategory> categoryRoot = criteriaQuery.from(SubCategory.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<SubCategory> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			categories  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return categories;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch SubCategory in DB");
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
