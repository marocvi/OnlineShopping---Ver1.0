package com.test.tesKnowledge;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {
		Configuration config  = new Configuration().configure().addAnnotatedClass(Car.class).addAnnotatedClass(Employee.class);
		SessionFactory sf = config.buildSessionFactory();
		//Creat Object 
		Session ss = sf.openSession();
		ss.setHibernateFlushMode(FlushMode.MANUAL);
		ss.beginTransaction();
		//Code go here
		Employee em2 = new Employee();
		em2.setName("hai");
		ss.persist(em2);
		ss.flush();
		em2.setName("goi ten");
		ss.flush();
		System.out.println("before flush");
		
		ss.getTransaction().commit();
		System.out.println("Transaction comit");
		ss.close();
		System.out.println("close session");
	}
}
