package com.hai.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieUtil {

	/**
	 * Delete cookie by Name
	 * @param cookieName
	 * @param request
	 */
	public void deleteCookie(String cookieName, HttpServletRequest request, HttpServletResponse response);
	/**
	 * Delete cookies by number of Name
	 * @param cookieNames
	 * @param request
	 */
	public void deleteCookies(String[] cookieNames, HttpServletRequest request);
	
}
