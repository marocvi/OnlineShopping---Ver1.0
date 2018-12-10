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
		
		ss.flush();
	
		
		ss.getTransaction().commit();
		ss.close();
		System.out.println("close session");
	}
}
