package com.hai.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtilImpl implements CookieUtil {
	public CookieUtilImpl() {
	}

	@Override
	public void deleteCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieName)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
	}	

	@Override
	public void deleteCookies(String[] cookieNames, HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

}
