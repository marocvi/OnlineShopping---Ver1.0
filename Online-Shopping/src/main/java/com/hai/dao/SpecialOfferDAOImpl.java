package com.hai.dao;

import java.util.ArrayList;
import java.util.Date;
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

import com.hai.idao.ISpecialOfferDAO;
import com.hai.model.SpecialOffer;
/**
 * This class provide implementation of {@link com.hai.idao.ISubcategoryDAO}
 * @see org.hibernate.Session
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
@SuppressWarnings("unchecked")
public class SpecialOfferDAOImpl implements ISpecialOfferDAO{
	private Session session;
	private SessionFactory sessionFactory;
	private final Logger LOGGER; 
	
	
	/**
	 *	Inject SessionFactory instance to open session
	 * @param sessionFactory
	 */
	public SpecialOfferDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER = Logger.getLogger(SpecialOfferDAOImpl.class);
	}
	
	
	@Override
	public boolean save(SpecialOffer specialOffer) {
		LOGGER.debug("Call save SpecialOffer");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code save specialOffer go here
			session.save(specialOffer);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Save SpecialOffer sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't save SpecialOffer into DB");
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
	public boolean update(SpecialOffer specialOffer) {
		LOGGER.debug("Call update SpecialOffer");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code update subCategodry go here
			session.update(specialOffer);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Update SpecialOffer sucessfully in DB");
			return true;
			
		}
		catch(Exception e ) {
			LOGGER.error("Can't update SpecialOffer in DB");
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
	public boolean delete(SpecialOffer specialOffer) {
		LOGGER.debug("Call delete SpecialOffer");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		try {
			session.beginTransaction();
			//Code delete subCategodry go here
			session.delete(specialOffer);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Delete SpecialOffer sucessfully in DB");
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
		
		LOGGER.debug("Call delete SpecialOffer");
		//Open  new session 
		session = sessionFactory.openSession();
	
		try {
			session.beginTransaction();
			
			//Code delete subCategodry go here
			String hql = "delete from SpecialOffer where id = :specialOfferID";
			Query<SpecialOffer> query = session.createQuery(hql);
			query.setParameter("specialOfferID", subCartgoryID);
			
			//Sesion automatically flush after calling this method
			query.executeUpdate();
			session.getTransaction().commit();
			LOGGER.debug("Delete SpecialOffer sucessfully in DB");
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
	public SpecialOffer findById(Integer specialOfferID) {
		LOGGER.debug("Call find SpecialOffer by ID");
		//Open  new session 
		session = sessionFactory.openSession();
		//Munual session flushing for better control
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		SpecialOffer specialOffer = null;
		
		try {
			session.beginTransaction();
			//Code fetch specialOffer go here
			specialOffer = session.get(SpecialOffer.class, specialOfferID);
			session.flush();
			session.getTransaction().commit();
			LOGGER.debug("Fetch SpecialOffer sucessfully in DB");
			return specialOffer;
			
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
	public List<SpecialOffer> findAll() {
		LOGGER.debug("Call find  all SpecialOffer ");
		//Open  new session 
		session = sessionFactory.openSession();
		
		List<SpecialOffer> specialOffers = new ArrayList<>();
		
		try {
			session.beginTransaction();
			//Code fetch specialOffer go here
			Query<SpecialOffer> query = session.createQuery("From SpecialOffer Where createdDate< :CurDate and endDate >=: CurDate ");
			query.setParameter("CurDate", new Date());
			specialOffers = query.getResultList();
			
			session.getTransaction().commit();
			LOGGER.debug("Fetch all SpecialOffer sucessfully in DB");
			return specialOffers;
			
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
	public List<SpecialOffer> findByProperty(String name, Object proValue) {
		LOGGER.debug("Call find SpecialOffer fit property condition");
		//Open session
		session = sessionFactory.openSession();
		List<SpecialOffer> specialOffers = null ;
		
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<SpecialOffer> criteriaQuery = builder.createQuery(SpecialOffer.class);
			Root<SpecialOffer> categoryRoot = criteriaQuery.from(SpecialOffer.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get(name), paramValue));
			Query<SpecialOffer> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, proValue);
			specialOffers  = query.getResultList();
			session.getTransaction().commit();
			LOGGER.debug("Fetch Subactegory sucessfully");
			return specialOffers;
		}
		catch(Exception e) {
			LOGGER.error("Can't fetch SpecialOffer in DB");
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
