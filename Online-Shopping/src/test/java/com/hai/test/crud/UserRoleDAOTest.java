package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.UserRoleDAOImpl;
import com.hai.model.Role;
import com.hai.model.UserRole;
import com.hai.model.Users;
import com.hai.util.SessionFactoryBuilderUtil;

public class UserRoleDAOTest {

	
	private static SessionFactory sessionFactory;
	private static UserRoleDAOImpl userRoleDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		userRoleDAO = new UserRoleDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveUserRole() {
		UserRole userRole = new UserRole();
		userRole.setUser(new Users());
		userRole.setRole(new Role());
		assertTrue(userRoleDAO.save(userRole));
	}
//	@Test //Ok
	public void testUpdateUserRole() {
		UserRole userRole = new UserRole();
		userRole.setId(3);
		assertTrue(userRoleDAO.update(userRole));
	}
//	@Test //ok
	public void testDeleteUserRole() {
		UserRole userRole = userRoleDAO.findById(1);
		assertTrue(userRoleDAO.delete(userRole));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(userRoleDAO.delete(2));
	}
	@Test //ok
	public void testFindAll() {
		
		assertEquals(1, userRoleDAO.findAll().size(),0);
	}
	
//	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, userRoleDAO.findByProperty("name", "Samsung s7").size());
	}
	
}















