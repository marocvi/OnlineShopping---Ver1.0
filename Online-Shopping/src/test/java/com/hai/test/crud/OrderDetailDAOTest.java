package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.OrderDetailDAOImpl;
import com.hai.model.Order;
import com.hai.model.OrderDetail;
import com.hai.model.Product;
import com.hai.model.Users;
import com.hai.util.SessionFactoryBuilderUtil;

public class OrderDetailDAOTest {

	
	private static SessionFactory sessionFactory;
	private static OrderDetailDAOImpl orderDetailDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		orderDetailDAO = new OrderDetailDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveOrderDetail() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(new Product());
		Order order = new Order();
		order.setOrderUser(new Users());
		order.setProcessUser(new Users());
		orderDetail.setOrder(order);
		
		
		assertTrue(orderDetailDAO.save(orderDetail));
	}
//	@Test //Ok
	public void testUpdateOrderDetail() {
		OrderDetail orderDetail = orderDetailDAO.findById(1);
		orderDetail.setAmount(100);
		assertTrue(orderDetailDAO.update(orderDetail));
	}
//	@Test //ok
	public void testDeleteOrderDetail() {
		OrderDetail orderDetail = orderDetailDAO.findById(8);
//		List<Product> products = new ArrayList<Product>();
//		ProductDAOImpl productDAO = new ProductDAOImpl(sessionFactory);
//		Product product = productDAO.findById(6);
//		products.add(product);
//		orderDetail.setId(6);
//		orderDetail.setProducts(products);
//		System.out.println();
		assertTrue(orderDetailDAO.delete(orderDetail));
	}
	
	
	@Test //ok
	public void testDeleteByID() {
		assertTrue(orderDetailDAO.delete(7));
	}
	
//	@Test //ok
	public void testFindByID() {
		assertNotNull(orderDetailDAO.findById(2));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(2, orderDetailDAO.findAll().size(),0);
	}
	
	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, orderDetailDAO.findByProperty("amount", 100).size());
	}
	
}















