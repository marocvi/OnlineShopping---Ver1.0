package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.UserDAOImpl;
import com.hai.model.Users;
import com.hai.util.SessionFactoryBuilderUtil;

public class UserDAOTest {

	
	private static SessionFactory sessionFactory;
	private static UserDAOImpl userDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		userDAO = new UserDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveUsers() {
		Users user = new Users();
		assertTrue(userDAO.save(user));
	}
//	@Test //Ok
	public void testUpdateUsers() {
		Users user = new Users();
		user.setId(3);
		user.setName("hai");
		assertTrue(userDAO.update(user));
	}
//	@Test //ok
	public void testDeleteUsers() {
		Users user = userDAO.findById(1);
		assertTrue(userDAO.delete(user));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(userDAO.delete(2));
	}
	
//	@Test //ok
	public void testFindByID() {
		assertNotNull(userDAO.findById(1));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(2, userDAO.findAll().size(),0);
	}
	
	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, userDAO.findByProperty("name","hai").size());
	}
	
}















