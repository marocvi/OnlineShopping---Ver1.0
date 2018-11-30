package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.CommentDAOImpl;
import com.hai.model.Comments;
import com.hai.model.Users;
import com.hai.util.SessionFactoryBuilderUtil;

public class CommentDAOTest {

	
	private static SessionFactory sessionFactory;
	private static CommentDAOImpl commentDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		commentDAO = new CommentDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSaveComments() {
		Comments comment = new Comments();
		comment.setUser(new Users());
		assertTrue(commentDAO.save(comment));
	}
//	@Test //Ok
	public void testUpdateComments() {
		Comments comment = new Comments();
		comment.setId(4);
		comment.setUser(new Users());
		comment.setContent("Goi ten em trong dem");
		assertTrue(commentDAO.update(comment));
	}
//	@Test //ok
	public void testDeleteComments() {
		Comments comment = commentDAO.findById(2);
		assertTrue(commentDAO.delete(comment));
	}
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(commentDAO.delete(2));
	}
	
//	@Test //ok
	public void testFindByID() {
		assertNotNull(commentDAO.findById(4));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(5, commentDAO.findAll().size(),0);
	}
	
	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, commentDAO.findByProperty("content","Goi ten em trong dem").size());
	}
	
}















