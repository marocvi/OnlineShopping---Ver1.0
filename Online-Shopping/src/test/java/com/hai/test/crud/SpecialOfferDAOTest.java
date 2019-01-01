package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.SpecialOfferDAOImpl;
import com.hai.model.SpecialOffer;
import com.hai.util.SessionFactoryBuilderUtil;

public class SpecialOfferDAOTest {

	
	private static SessionFactory sessionFactory;
	private static SpecialOfferDAOImpl specialOfferDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		specialOfferDAO = new SpecialOfferDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveSpecialOffer() {
		SpecialOffer specialOffer = new SpecialOffer();
		assertTrue(specialOfferDAO.save(specialOffer));
	}
//	@Test //Ok
	public void testUpdateSpecialOffer() {
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(3);
		assertTrue(specialOfferDAO.update(specialOffer));
	}
//	@Test //ok
	public void testDeleteSpecialOffer() {
		SpecialOffer specialOffer = specialOfferDAO.findById(1);
		assertTrue(specialOfferDAO.delete(specialOffer));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(specialOfferDAO.delete(2));
	}
	
//	@Test //ok
	public void testFindByID() {
		assertNotNull(specialOfferDAO.findById(1));
	}
	@Test //ok
	public void testFindAll() {
		
		assertEquals(1, specialOfferDAO.findAll().size(),0);
	}
	
//	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, specialOfferDAO.findByProperty("SpecialOffer","Normal").size());
	}
	
}















