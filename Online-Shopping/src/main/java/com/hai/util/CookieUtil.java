package com.hai.util;

import javax.servlet.http.HttpServletRequest;

public interface CookieUtil {

	/**
	 * Delete cookie by Name
	 * @param cookieName
	 * @param request
	 */
	public void deleteCookie(String cookieName, HttpServletRequest request);
	/**
	 * Delete cookies by number of Name
	 * @param cookieNames
	 * @param request
	 */
	public void deleteCookies(String[] cookieNames, HttpServletRequest request);
	
}
