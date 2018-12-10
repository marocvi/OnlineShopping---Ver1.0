package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.RoleDAOImpl;
import com.hai.model.Role;
import com.hai.util.SessionFactoryBuilderUtil;

public class RoleDAOTest {

	
	private static SessionFactory sessionFactory;
	private static RoleDAOImpl roleDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		roleDAO = new RoleDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveRole() {
		Role role = new Role();
		assertTrue(roleDAO.save(role));
	}
//	@Test //Ok
	public void testUpdateRole() {
		Role role = new Role();
		role.setId(3);
		role.setRole("Admin");
		assertTrue(roleDAO.update(role));
	}
//	@Test //ok
	public void testDeleteRole() {
		Role role = roleDAO.findById(1);
		assertTrue(roleDAO.delete(role));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(roleDAO.delete(2));
	}
	
//	@Test //ok
	public void testFindByID() {
		assertNotNull(roleDAO.findById(1));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(3, roleDAO.findAll().size(),0);
	}
	
	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, roleDAO.findByProperty("Role","Normal").size());
	}
	
}















