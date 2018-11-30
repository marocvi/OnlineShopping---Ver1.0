package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.OrderDAOImpl;
import com.hai.dao.UserDAOImpl;
import com.hai.model.Order;
import com.hai.model.Users;
import com.hai.util.SessionFactoryBuilderUtil;

public class OrderDAOTest {

	private static SessionFactory sessionFactory;
	private static OrderDAOImpl orderDAO;

	@BeforeClass
	public static void seup() {
		// Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		orderDAO = new OrderDAOImpl(sessionFactory);

	}

	@Test //ok
	public void testSaveOrder() {
		Order order = new Order();
//		Users user1 = new UserDAOImpl(sessionFactory).findById(3);
//		Users user2 = new UserDAOImpl(sessionFactory).findById(4);
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Users user1 = session.get(Users.class, 3);
//		Users user2 = session.get(Users.class, 4);
		order.setOrderUser(new Users());
		order.setProcessUser(new Users());
//		session.persist(order);
		assertTrue(orderDAO.save(order));
	}

//	@Test //Ok
	public void testUpdateOrder() {
		Order order = orderDAO.findById(2);
		order.setOrderUser(new UserDAOImpl(sessionFactory).findById(3));
		order.setAmountTotal(100);
		assertTrue(orderDAO.update(order));
	}

//	@Test //ok
	public void testDeleteOrder() {
		Order order = orderDAO.findById(1);
		assertTrue(orderDAO.delete(order));
	}

//	@Test //ok
	public void testDeleteByID() {
		assertTrue(orderDAO.delete(2));
	}

//	@Test //ok
	public void testFindByID() {
		assertNotNull(orderDAO.findById(1));
	}

//	@Test //ok
	public void testFindAll() {

		assertEquals(2, orderDAO.findAll().size(), 0);
	}

//	@Test // ok
	public void testFindByProperty() {
		assertEquals(1, orderDAO.findByProperty("amountTotal",100).size());
	}

}
