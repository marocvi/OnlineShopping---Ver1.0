package com.hai.test.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hai.dao.GuestDAOImpl;
import com.hai.model.Guest;
import com.hai.util.SessionFactoryBuilderUtil;

public class GuestDAOTest {

	private static SessionFactory sessionFactory;
	private static GuestDAOImpl guestDAO;

	@BeforeClass
	public static void seup() {
		// Initilization
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		guestDAO = new GuestDAOImpl(sessionFactory);

	}

//	@Test //ok
	public void testSaveGuest() {
		Guest guest = new Guest();
		assertTrue(guestDAO.save(guest));
	}

//	@Test //Ok
	public void testUpdateGuest() {
		Guest guest = new Guest();
		guest.setId(2);
		guest.setSession("hai");
		assertTrue(guestDAO.update(guest));
	}

//	@Test //ok
	public void testDeleteGuest() {
		Guest guest = guestDAO.findById(4);
		assertTrue(guestDAO.delete(guest));
	}

//	@Test //ok
	public void testDeleteByID() {
		assertTrue(guestDAO.delete(3));
	}

//	@Test //ok
	public void testFindByID() {
		assertNotNull(guestDAO.findById(2));
	}

//	@Test //ok
	public void testFindAll() {

		assertEquals(2, guestDAO.findAll().size(), 0);
	}

	@Test // ok
	public void testFindByProperty() {
		assertEquals(1, guestDAO.findByProperty("session", "hai").size());
	}

}
