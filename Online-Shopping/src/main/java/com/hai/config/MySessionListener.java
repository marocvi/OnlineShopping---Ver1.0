package com.hai.config;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hai.util.Currency;


public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
		HttpSession session = se.getSession();
		session.setAttribute("currency", Currency.DOLLA.toString());
		session.setAttribute("rate",1);
		session.setAttribute("selectedCurrency",Currency.DOLLA);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//DO nothing
		
	}

}
