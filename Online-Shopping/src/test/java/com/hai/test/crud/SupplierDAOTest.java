package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.SupplierDAOImpl;
import com.hai.model.Product;
import com.hai.model.Supplier;
import com.hai.util.SessionFactoryBuilderUtil;

public class SupplierDAOTest {

	
	private static SessionFactory sessionFactory;
	private static SupplierDAOImpl supplierDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		supplierDAO = new SupplierDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveSupplier() {
		Supplier supplier = new Supplier();
		supplier.setName("Apple");
		List<Product> products = new ArrayList<Product>();
		List<Supplier> suppliers = new ArrayList<Supplier>();
		suppliers.add(supplier);
		Product product = new Product();
		product.setSuppliers(suppliers);
		product.setName("Iphone 5s");
		
		products.add(product);
		supplier.setProducts(products);
		assertTrue(supplierDAO.save(supplier));
	}
//	@Test //Ok
	public void testUpdateSupplier() {
		Supplier supplier = new Supplier();
		supplier.setId(4);
		supplier.setName("ASUS");
		assertTrue(supplierDAO.update(supplier));
	}
//	@Test //ok
	public void testDeleteSupplier() {
		Supplier supplier = supplierDAO.findById(8);
//		List<Product> products = new ArrayList<Product>();
//		ProductDAOImpl productDAO = new ProductDAOImpl(sessionFactory);
//		Product product = productDAO.findById(6);
//		products.add(product);
//		supplier.setId(6);
//		supplier.setProducts(products);
//		System.out.println();
		assertTrue(supplierDAO.delete(supplier));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(supplierDAO.delete(7));
	}
	
//	@Test //ok
	public void testFindByID() {
		assertNotNull(supplierDAO.findById(4));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(5, supplierDAO.findAll().size(),0);
	}
	
	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, supplierDAO.findByProperty("name", "ASUS").size());
	}
	
}















