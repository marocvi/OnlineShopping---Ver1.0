package com.hai.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;

import com.hai.util.SessionFactoryBuilderUtil;

public class MyServletContextListener  implements  ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//What to do when app close . For now it do nothing.
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//Initilize the Session Factory
		SessionFactory sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("sessionFactory", sessionFactory);
	}

}
