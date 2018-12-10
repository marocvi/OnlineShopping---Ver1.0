package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.PriceDAOImpl;
import com.hai.model.Price;
import com.hai.model.Product;
import com.hai.util.SessionFactoryBuilderUtil;

public class PriceDAOTest {

	
	private static SessionFactory sessionFactory;
	private static PriceDAOImpl priceDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		priceDAO = new PriceDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSavePrice() {
		Price price = new Price();
		price.setProduct(new Product());
		assertTrue(priceDAO.save(price));
	}
//	@Test //Ok
	public void testUpdatePrice() {
		Price price = new Price();
		price.setId(3);
		price.setProduct(new Product());
		price.setUnitPrice(100);
		assertTrue(priceDAO.update(price));
	}
//	@Test //ok
	public void testDeletePrice() {
		Price price = priceDAO.findById(1);
		assertTrue(priceDAO.delete(price));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(priceDAO.delete(2));
	}
	
	@Test //ok
	public void testFindByID() {
		System.out.println(Math.round(11/12.0));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(1, priceDAO.findAll().size(),0);
	}
	
//	@Test //ok
	public void testFindByProperty() {
		assertEquals(2, priceDAO.findByProperty("unitPrice",100.0).size());
	}
	
}















