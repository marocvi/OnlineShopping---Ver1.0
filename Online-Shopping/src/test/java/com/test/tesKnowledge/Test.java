package com.test.tesKnowledge;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {
		Configuration config  = new Configuration().configure().addAnnotatedClass(Car.class).addAnnotatedClass(Employee.class);
		SessionFactory sf = config.buildSessionFactory();
		//Creat Object 
		Employee em = new Employee();
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car();
		car.setEngine("V30");
		cars.add(car);
		em.setCars(cars);
//		car.setEmployee( em);
		
		Session ss = sf.openSession();
		ss.beginTransaction();
		//Code go here
		ss.persist(car);
		ss.persist(em);
//		em = ss.get(Employee.class, 1);
//		Car car  = em.getCars().get(0);
		
//		System.out.println(car.getEngine());
		
		
		ss.getTransaction().commit();
		ss.close();
	}
}
