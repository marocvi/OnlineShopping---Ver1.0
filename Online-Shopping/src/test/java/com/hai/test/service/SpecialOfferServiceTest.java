package com.hai.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.iservice.ISpecialOfferService;
import com.hai.service.SpecialOfferServiceImpl;
import com.hai.util.SessionFactoryBuilderUtil;

public class SpecialOfferServiceTest {
	private static ISpecialOfferService specialOfferService;
	
	@BeforeClass
	public static void setup() {
		specialOfferService = new SpecialOfferServiceImpl(SessionFactoryBuilderUtil.getSessionFactory());
		
	}
	
	@Test
	public void testGetAllValidOffer() {
		assertEquals(1, specialOfferService.getListOfValidSpecialOffers().size());
	}
}
