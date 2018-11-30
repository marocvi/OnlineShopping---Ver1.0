package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.CategoryDAOImpl;
import com.hai.model.Category;
import com.hai.util.SessionFactoryBuilderUtil;

public class CategoryDAOTest {
	private static CategoryDAOImpl categoryDAO ;

	@BeforeClass
	public static void setup() {
		SessionFactory sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		categoryDAO = new CategoryDAOImpl(sessionFactory);
	}
//	@Test //Ok
	public void tesSaveCategory() {
		Category category = new Category();
		category.setName("Education");
		assertTrue(categoryDAO.save(category)); 
	}
//	@Test //ok
	public void testUpdateCategory() {
		Category category = new Category();
		category.setId(3);
		category.setName("Electronics");
		assertTrue(categoryDAO.update(category));
	}
	
//	@Test //ok
	public void testDeleteCategory() {
		Category category = new Category();
		category.setId(11);
		assertTrue(categoryDAO.delete(category));
	}
	
//	@Test //ok
	public void testDeleteCategorybyID() {
		assertTrue(categoryDAO.delete(12));
	}
	
	@Test //ok
	public void testFindALL() {
		assertEquals(8, categoryDAO.findAll().size(),0);
	}
	
//	@Test //ok
	public void testFindByProperty() {
		assertEquals(2, categoryDAO.findByProperty("name", "Education").size());
	}
	
	
//	@Test //ok
	public void testFindCategoryByID() {
		assertNotNull(categoryDAO.findById(1));
	}
	
	
	
	
}
