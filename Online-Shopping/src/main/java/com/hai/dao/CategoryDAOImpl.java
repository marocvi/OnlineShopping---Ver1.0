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

import com.hai.idao.ICategoryDAO;
import com.hai.model.Category;

/**
 * This class is implementation of <{@link com.hai.idao.ICategoryDAO} interface 
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class CategoryDAOImpl implements ICategoryDAO{

	private SessionFactory sessionFactory;
	private Session session;
	private  final Logger LOGGER;
	/**
	 *  <p>Initilize sessionFactory to create session.
	 *  When we use this DAO we need to pass SessionFactory intance 
	 *  which is possibly from context.
	 *  <p>
	 *  <b>Note: </b> Because to create SessionFactory object is heavy 
	 *  so there is only one instance of SessionFactory is created in one project
	 * @param sessionFactory 
	 */
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(CategoryDAOImpl.class.getName());
	}
	
	@Override
	public boolean save(Category category) {
		LOGGER.debug("Call method for saving Category into DB");
		//Open new session
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		//Code saving category to Database
		try {
			session.persist(category);
			session.persist(category);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Category is saved to DB");
		}
		catch(Exception e) {
			LOGGER.error("Can't save Category to DB");
			e.printStackTrace();
			return false;
		}
		
		finally {
			//Close session
			session.close();
		}
		
		return true;
		
	}

	@Override
	public boolean update(Category category) {
		LOGGER.debug("Call update Category method");
		//Open new session
		session = sessionFactory.openSession();
		//Use manual flush for better control.
		session.setHibernateFlushMode(FlushMode.MANUAL);
		//Code update Category to DB
		try {
			
			session.beginTransaction();
			session.update(category);
			session.flush();
			session.getTransaction().commit();
		LOGGER.debug("Category is updated to DB");
					
		}
		catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			LOGGER.error("Can't save Category to DB");
			return false;
		}
		
		finally {
			//Close session
			session.close();
		}
	
		return true;
	}

	@Override
	public boolean delete(Category category) {
		LOGGER.debug("Call delete Category");
		//Open new session
		session = sessionFactory.openSession();
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			session.delete(category);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Category in DB");
			return true;
		}
		catch(Exception e){
			LOGGER.error("Can't delete Category in DB");
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		
		finally {
			//Close session
			session.close();
		}
		
	}
	/**
	 * This method delete category by id using query. No need Flusing
	 * @param cartgoryID 
	 */
	@Override
	public boolean delete(Integer cartgoryID) {
		LOGGER.debug("Call delete Category ");
		//Open session
		session = sessionFactory.openSession();
		String hql = "Delete from Category where id =:categoryID";
		try {
			session.beginTransaction();
			Query<Category> query = session.createQuery(hql);
			query.setParameter("categoryID", cartgoryID);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			LOGGER.error("Can't delete Category in DB");
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		finally {
			//Close session
			session.close();
		}
	}

	@Override
	public Category findById(Integer categoryID) {
		LOGGER.debug("Call find Category by ID");
		//Open session
		session = sessionFactory.openSession();
		session.setHibernateFlushMode(FlushMode.MANUAL);
		Category category = null;
		try {
			session.beginTransaction();
			category = session.get(Category.class, categoryID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Category sucessfully!");;
		}
		catch(Exception e) {
			LOGGER.error("Can't find Category in DB");
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally {
			//Close session
			session.close();
		}
		return category;
	}

	@Override
	public List<Category> findAll() {
		LOGGER.debug("Call find all Categories in DB");
		//Open session
		session = sessionFactory.openSession();
		List<Category> categories = null ;
		try {
			session.beginTransaction();
			Query<Category> query = session.createQuery("From Category");
			categories = query.getResultList();
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Found categories");
		}
		catch(Exception e) {
			LOGGER.error("Can't find categories in DB");
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally {
			//Close session
			session.close();
		}
		return categories;
	}

	@Override
	public List<Category> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Category fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Category> categories = null ;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Category> criteriaQuery = builder.createQuery(Category.class);
			Root<Category> categoryRoot = criteriaQuery.from(Category.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Category> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			categories  = query.getResultList();
			LOGGER.debug("Fetch some Categories sucessfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("Can't fetch Categories in DB");
		}
		finally {
			//Close session
			session.close();
		}
		return categories;
	}

}

























