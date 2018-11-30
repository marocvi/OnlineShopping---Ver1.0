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

import com.hai.idao.IOrderDetailDAO;
import com.hai.model.OrderDetail;
/**
 * This class provide implementation of {@link com.hai.idao.IOrderDetailDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class OrderDetailDAOImpl implements IOrderDetailDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public OrderDetailDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SubcategoryDAOImpl.class);
	}
	
	
	public boolean save(OrderDetail orderDetail) {
		LOGGER.debug("Call save OrderDetail");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save orderDetail go here
			session.persist(orderDetail);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save OrderDetail sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save OrderDetail into DB");
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

	public boolean update(OrderDetail orderDetail) {
		LOGGER.debug("Call update OrderDetail");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(orderDetail);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update OrderDetail sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update OrderDetail in DB");
			e.printStackTrace();
			//If there is error , nothing saved to DB
			session.getTransaction().rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	public boolean delete(OrderDetail orderDetail) {
		LOGGER.debug("Call delete OrderDetail");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(orderDetail);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete OrderDetail sucessfully in DB");
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
		
		LOGGER.debug("Call delete OrderDetail");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from OrderDetail where id = :orderDetailID";
			Query<OrderDetail> query = session.createQuery(hql);
			query.setParameter("orderDetailID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete OrderDetail sucessfully in DB");
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

	public OrderDetail findById(Integer orderDetailID) {
		LOGGER.debug("Call find OrderDetail by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		OrderDetail orderDetail = null;
		
		try {
			session.beginTransaction();
			//Code fetch orderDetail go here
			orderDetail = session.get(OrderDetail.class, orderDetailID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch OrderDetail sucessfully in DB");
			return orderDetail;
			
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

	public List<OrderDetail> findAll() {
		LOGGER.debug("Call find  all OrderDetail ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<OrderDetail> orderDetails = null;
		
		try {
			session.beginTransaction();
			//Code fetch orderDetail go here
			Query<OrderDetail> query = session.createQuery("From OrderDetail");
			orderDetails = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch all OrderDetail sucessfully in DB");
			return orderDetails;
			
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

	public List<OrderDetail> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find OrderDetail fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<OrderDetail> orderDetails = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OrderDetail> criteriaQuery = builder.createQuery(OrderDetail.class);
			Root<OrderDetail> categoryRoot = criteriaQuery.from(OrderDetail.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<OrderDetail> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			orderDetails  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return orderDetails;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch OrderDetail in DB");
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
