package com.hai.test.service;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.iservice.IRoleService;
import com.hai.service.RoleServiceImpl;
import com.hai.util.SessionFactoryBuilderUtil;

public class RoleServiceTest {

	private static IRoleService roleService;
	@BeforeClass
	public static void setup() {
		SessionFactory sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		roleService = new RoleServiceImpl(sessionFactory);
	}
	
	@Test
	public void testFind() {
		assertNotNull(roleService.findByRoleName("Normal"));
	}
}
