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

import com.hai.idao.IPriceDAO;
import com.hai.model.Price;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class PriceDAOImpl implements IPriceDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public PriceDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	@Override
	public boolean save(Price price) {
		LOGGER.debug("Call save Price");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save price go here
			session.save(price);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save Price sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save Price into DB");
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
	public boolean update(Price price) {
		LOGGER.debug("Call update Price");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(price);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update Price sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update Price in DB");
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
	public boolean delete(Price price) {
		LOGGER.debug("Call delete Price");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(price);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete Price sucessfully in DB");
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
		
		LOGGER.debug("Call delete Price");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from Price where id = :priceID";
			Query<Price> query = session.createQuery(hql);
			query.setParameter("priceID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete Price sucessfully in DB");
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
	public Price findById(Integer priceID) {
		LOGGER.debug("Call find Price by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Price price = null;
		
		try {
			session.beginTransaction();
			//Code fetch price go here
			price = session.get(Price.class, priceID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Price sucessfully in DB");
			return price;
			
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
	public List<Price> findAll() {
		LOGGER.debug("Call find  all Price ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<Price> prices = null;
		
		try {
			session.beginTransaction();
			//Code fetch price go here
			Query<Price> query = session.createQuery("From Price");
			prices = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all Price sucessfully in DB");
			return prices;
			
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
	public List<Price> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find Price fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<Price> prices = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Price> criteriaQuery = builder.createQuery(Price.class);
			Root<Price> categoryRoot = criteriaQuery.from(Price.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<Price> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			prices  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return prices;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch Price in DB");
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
