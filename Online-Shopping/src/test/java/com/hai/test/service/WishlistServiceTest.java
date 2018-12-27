package com.hai.test.service;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.UserDAOImpl;
import com.hai.iservice.IWishListService;
import com.hai.service.WishlistServiceImpl;
import com.hai.util.SessionFactoryBuilderUtil;

public class WishlistServiceTest {
	private static IWishListService wishlistService;
	private static SessionFactory sessionFactory;
	

	@BeforeClass
	public static void setup() {
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		wishlistService = new WishlistServiceImpl(sessionFactory);
	}

	@Test
	public void testGetProductByPage() {
		assertEquals(9, wishlistService.getProductByPage(0, 12,new UserDAOImpl(sessionFactory).findById(1)).size());
	}
}
