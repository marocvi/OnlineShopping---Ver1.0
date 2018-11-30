package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.SubcategoryDAOImpl;
import com.hai.model.Category;
import com.hai.model.SubCategory;
import com.hai.util.SessionFactoryBuilderUtil;

public class SubcategoryDAOTest {

	
	private static SessionFactory sessionFactory;
	private static SubcategoryDAOImpl subCategoryDAO;
	
	@BeforeClass
	public static void seup() {
		//Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		subCategoryDAO = new SubcategoryDAOImpl(sessionFactory);
		
	}
//	@Test //ok
	public void testSavSubCategory() {
		SubCategory subcategory = new SubCategory();
		subcategory.setName("Phone");
		Category category = new Category();
		category.setId(14);
		subcategory.setCategory(category);
		assertTrue(subCategoryDAO.save(subcategory));
	}
//	@Test Ok
	public void testUpdateSubCategory() {
		SubCategory subCategory = new SubCategory();
		subCategory.setId(3);
		subCategory.setName("Phone");
		Category category = new Category();
		category.setId(3);
		subCategory.setCategory(category);
		assertTrue(subCategoryDAO.update(subCategory));
	}
//	@Test //ok
	public void testDeleteSubCategory() {
		SubCategory subCategory = new SubCategory();
		subCategory.setId(3);
		Category category = new Category();
		category.setId(3);
		subCategory.setCategory(category);
		assertTrue(subCategoryDAO.delete(subCategory));
	}
	
	
//	@Test //ok
	public void testDeleteByID() {
		assertTrue(subCategoryDAO.delete(2));
	}
//	@Test //ok
	public void testFindAll() {
		
		assertEquals(2, subCategoryDAO.findAll().size(),0);
	}
	
//	@Test //ok
	public void testFindByProperty() {
		assertEquals(1, subCategoryDAO.findByProperty("name", "TV").size());
	}
	
}















