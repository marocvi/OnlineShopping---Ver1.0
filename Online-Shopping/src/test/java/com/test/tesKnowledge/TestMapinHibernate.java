package com.test.tesKnowledge;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestMapinHibernate {

	public static void main(String[] args) {
		Configuration config  = new Configuration().configure().addAnnotatedClass(Author.class).addAnnotatedClass(Book.class);
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		session.beginTransaction();
		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		session.persist(a);

		Book b = new Book();
		b.setTitle("Hibernate Tips");
		b.getAuthor().add(a);
		session.persist(b);

		a.getBooks().put(b.getTitle(), b);
	}
}
