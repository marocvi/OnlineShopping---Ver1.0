package com.hai.test.crud;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.CartDAOImpl;
import com.hai.idao.ICartDAO;
import com.hai.model.Cart;
import com.hai.util.SessionFactoryBuilderUtil;

public class CartTest {
	
	private static ICartDAO cartDAO;
	
	@BeforeClass
	public static void setup() {
		//Get session Factory and initilize cartDAO
		SessionFactory sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		cartDAO = new CartDAOImpl(sessionFactory);	
	}
	
	
	@Test
	public void testSave() {
		Cart cart = new Cart();
		cartDAO.save(cart);
		assertEquals("Sucess!",1,cartDAO.findAll().size());
	}
	@Test
	public void testFindALL() {
		
	}
	
}
