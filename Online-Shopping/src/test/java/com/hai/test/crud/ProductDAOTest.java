package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.ProductDAOImpl;
import com.hai.model.Product;
import com.hai.model.Supplier;
import com.hai.util.SessionFactoryBuilderUtil;

public class ProductDAOTest {

	
	private static SessionFactory sessionFactory;
	private static ProductDAOImpl productDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		productDAO = new ProductDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveProduct() {
		Product product = new Product();
		product.setName("Samsung s7");
		List<Supplier> suppliers = new ArrayList<Supplier>();
		suppliers.add( new Supplier());
		product.setSuppliers(suppliers);
		assertTrue(productDAO.save(product));
	}
//	@Test //Ok
	public void testUpdateProduct() {
		Product product = new Product();
		product.setId(3);
		product.setName("Iphone6s");
		assertTrue(productDAO.update(product));
	}
//	@Test //ok
	public void testDeleteProduct() {
		Product product = new Product();
		product.setId(4);
		assertTrue(productDAO.delete(product));
	}
	
	
	@Test //ok
	public void testDeleteByID() {
		assertTrue(productDAO.delete(6));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(1, productDAO.findAll().size(),0);
	}
	
	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, productDAO.findByProperty("name", "Samsung s7").size());
	}
	
}















