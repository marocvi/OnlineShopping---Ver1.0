package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.CartDetailDAOImpl;
import com.hai.dao.ProductDAOImpl;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Product;
import com.hai.util.SessionFactoryBuilderUtil;

public class CartDetailDAOTest {

	private static SessionFactory sessionFactory;
	private static CartDetailDAOImpl cartDetailDAO;

	@BeforeClass
	public static void seup() {
		// Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		cartDetailDAO = new CartDetailDAOImpl(sessionFactory);

	}

//	@Test //ok
	public void testSaveCartDetail() {
		CartDetail cartDetail = new CartDetail();
		cartDetail.setProduct(new Product());
		cartDetail.setCart(new Cart());
		
		assertTrue(cartDetailDAO.save(cartDetail));
	}

//	@Test //Ok
	public void testUpdateCartDetail() {
		CartDetail cartDetail = cartDetailDAO.findById(1);
		cartDetail.setAmount(12);
		cartDetail.setProduct(new ProductDAOImpl(sessionFactory).findById(8));

		assertTrue(cartDetailDAO.update(cartDetail));
	}

//	@Test //ok
	public void testDeleteCartDetail() {
		CartDetail cartDetail = cartDetailDAO.findById(1);
		assertTrue(cartDetailDAO.delete(cartDetail));
	}

	@Test //ok
	public void testDeleteByID() {
		assertTrue(cartDetailDAO.delete(1));
	}

//	@Test //ok
	public void testFindByID() {
		assertNotNull(cartDetailDAO.findById(1));
	}

//	@Test //ok
	public void testFindAll() {

		assertEquals(1, cartDetailDAO.findAll().size(), 0);
	}

//	@Test // ok
	public void testFindByProperty() {
		assertEquals(1, cartDetailDAO.findByProperty("amount",12).size());
	}

}
