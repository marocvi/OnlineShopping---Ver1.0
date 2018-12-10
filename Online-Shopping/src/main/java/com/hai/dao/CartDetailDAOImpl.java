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

import com.hai.idao.ICartDetailDAO;
import com.hai.model.CartDetail;
/**
 * This class provide implementation of {@link com.hai.idao.ICartDetailDetailDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class CartDetailDAOImpl implements ICartDetailDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public CartDetailDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	@Override
	public boolean save(CartDetail cartDetail) {
		LOGGER.debug("Call save CartDetail");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save cartDetail go here
			session.persist(cartDetail);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save CartDetail sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save CartDetail into DB");
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
	public boolean update(CartDetail cartDetail) {
		LOGGER.debug("Call update CartDetail");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.merge(cartDetail);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update CartDetail sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update CartDetail in DB");
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
	public boolean delete(CartDetail cartDetail) {
		LOGGER.debug("Call delete CartDetail");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(cartDetail);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete CartDetail sucessfully in DB");
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
	public boolean delete(Integer subCartDetailgoryID) {
		
		LOGGER.debug("Call delete CartDetail");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from CartDetail where id = :cartDetailID";
			Query<CartDetail> query = session.createQuery(hql);
			query.setParameter("cartDetailID", subCartDetailgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete CartDetail sucessfully in DB");
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
	public CartDetail findById(Integer cartDetailID) {
		LOGGER.debug("Call find CartDetail by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		CartDetail cartDetail = null;
		
		try {
			session.beginTransaction();
			//Code fetch cartDetail go here
			cartDetail = session.get(CartDetail.class, cartDetailID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch CartDetail sucessfully in DB");
			return cartDetail;
			
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
	public List<CartDetail> findAll() {
		LOGGER.debug("Call find  all CartDetail ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<CartDetail> cartDetails = null;
		
		try {
			session.beginTransaction();
			//Code fetch cartDetail go here
			Query<CartDetail> query = session.createQuery("From CartDetail");
			cartDetails = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all CartDetail sucessfully in DB");
			return cartDetails;
			
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
	public List<CartDetail> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find CartDetail fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<CartDetail> cartDetails = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CartDetail> criteriaQuery = builder.createQuery(CartDetail.class);
			Root<CartDetail> categoryRoot = criteriaQuery.from(CartDetail.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<CartDetail> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			cartDetails  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return cartDetails;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch CartDetail in DB");
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
