package com.hai.test.service;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.iservice.ICartService;
import com.hai.model.Cart;
import com.hai.service.CartServiceImpl;
import com.hai.util.SessionFactoryBuilderUtil;

public class CartServiceTest {
	private static ICartService cartService;
	
	@BeforeClass
	public static void setup() {
		
		cartService = new CartServiceImpl(SessionFactoryBuilderUtil.getSessionFactory());
	}
	@Test
	public void testSave() {
		
		Cart cart = new Cart();
		cart.setAmountTotal(9);
		cart.setMoneyTotal(56000);
		cartService.save(cart);
	};
	
}
