package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.ProductDAOImpl;
import com.hai.dao.WishlistDAOImpl;
import com.hai.model.Product;
import com.hai.model.Users;
import com.hai.model.Wishlist;
import com.hai.util.SessionFactoryBuilderUtil;

public class WishlistDAOTest {

	private static SessionFactory sessionFactory;
	private static WishlistDAOImpl wishlistDAO;

	@BeforeClass
	public static void seup() {
		// Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		wishlistDAO = new WishlistDAOImpl(sessionFactory);

	}

//	@Test //ok
	public void testSaveWishlist() {
		Wishlist wishlist = new Wishlist();
		wishlist.setProduct(new Product());
		assertTrue(wishlistDAO.save(wishlist));
	}

//	@Test //Ok
	public void testUpdateWishlist() {
		Wishlist wishlist = new Wishlist();
		wishlist.setId(2);
		wishlist.setProduct(new Product());
		wishlist.setUser(new Users());

		assertTrue(wishlistDAO.update(wishlist));
	}

//	@Test //ok
	public void testDeleteWishlist() {
		Wishlist wishlist = wishlistDAO.findById(4);
		assertTrue(wishlistDAO.delete(wishlist));
	}

//	@Test //ok
	public void testDeleteByID() {
		assertTrue(wishlistDAO.delete(2));
	}

//	@Test //ok
	public void testFindByID() {
		assertNotNull(wishlistDAO.findById(1));
	}

//	@Test //ok
	public void testFindAll() {

		assertEquals(1, wishlistDAO.findAll().size(), 0);
	}

	@Test // ok
	public void testFindByProperty() {
		assertEquals(1, wishlistDAO.findByProperty("product", new ProductDAOImpl(sessionFactory).findById(21)).size());
	}

}
