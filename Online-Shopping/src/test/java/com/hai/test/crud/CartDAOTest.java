package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.CartDAOImpl;
import com.hai.idao.ICartDAO;
import com.hai.model.Cart;
import com.hai.util.SessionFactoryBuilderUtil;

public class CartDAOTest {
	
	private static ICartDAO cartDAO;
	
	@BeforeClass
	public static void setup() {
		//Get session Factory and initilize cartDAO
		SessionFactory sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		cartDAO = new CartDAOImpl(sessionFactory);	
	}
	
	
	@Test //OK
	public void testSave() {
		Cart cart = new Cart();
		cart.setAmountTotal(9);
		cart.setMoneyTotal(56000);
		cartDAO.save(cart);
	}
//	@Test //OK
	public void testUpdate() {
		Cart cart = cartDAO.findById(3);
		cart.setAmountTotal(2);
		cartDAO.update(cart);
		assertEquals(10,cartDAO.findById(3).getAmountTotal(),0);
	}
//	@Test //OK
	public void testDelete() {
		Cart cart = cartDAO.findById(2);
		assertTrue(cartDAO.delete(cart));
	}
//	@Test //OK
	public void testDeteleById() {
		assertTrue(cartDAO.delete(1));
	}
	
//	@Test   //OK
	public void testFindById() {
		assertNotNull(cartDAO.findById(3));
	} 

	
	
//	@Test //ok
	public void testFindALL() {
		assertEquals(2, cartDAO.findAll().size());
		
	}
//	@Test //ok
	public void testFinByProperty() {
		assertEquals(1, cartDAO.findByProperty("amountTotal", 9).size(),0);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
