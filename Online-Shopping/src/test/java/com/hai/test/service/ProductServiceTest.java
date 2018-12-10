package com.hai.test.service;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.iservice.IProductService;
import com.hai.service.ProductServiceImpl;
import com.hai.util.SessionFactoryBuilderUtil;

public class ProductServiceTest {
	private static SessionFactory sessionFactory;
	private static IProductService productService;
	
	@BeforeClass
	public static void setup() {
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		productService = new ProductServiceImpl(sessionFactory);
	}
//	@Test
	public void testGetNumberOfProduc() {
		
		productService.getAllProduct();
		
		
	}
	@Test
	public void testListOfProductByPage() {
		assertEquals(12, productService.getProductByPage(4, 0, 12).size());
		
		
		
	}
	
	
}
