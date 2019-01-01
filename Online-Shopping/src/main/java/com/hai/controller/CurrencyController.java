package com.hai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hai.util.Currency;

/**
 * Servlet implementation class CurrencyController
 */
public class CurrencyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectedCurrency = request.getParameter("selectedCurrency");
		double rate = 1;
		HttpSession session = request.getSession();

		session.setAttribute("currency", Currency.valueOf(selectedCurrency).toString());
		switch (selectedCurrency) {
		case "DOLLA":
			rate = 1;
			break;
		case "EURO":
			rate = 0.874;
			break;
		case "YEN":
			rate = 110.83;
			break;
		}
		session.setAttribute("rate", rate);
		session.setAttribute("selectedCurrency", selectedCurrency);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
