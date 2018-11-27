package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Stack;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.CartDAOImpl;
import com.hai.idao.ICartDAO;
import com.hai.model.Cart;
import com.hai.model.Guest;
import com.hai.util.SessionFactoryBuilderUtil;
import com.test.tesKnowledge.Car;

public class CartTest {
	
	private static ICartDAO cartDAO;
	
	@BeforeClass
	public static void setup() {
		//Get session Factory and initilize cartDAO
		SessionFactory sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		cartDAO = new CartDAOImpl(sessionFactory);	
	}
	
	
//	@Test
	public void testSave() {
		Cart cart = new Cart();
		cart.setAmountTotal(100);
		cart.setGuest(new Guest());
		cart.setMoneyTotal(1000000);
	
		cartDAO.save(cart);

	}
//	@Test
	public void testUpdate() {
		
	}
//	@Test
	public void testFindALL() {
		
	}
	
//	@Test
	public void testFindByID() {
		assertNotNull("ok", cartDAO.findById(1));
	}
	
}
