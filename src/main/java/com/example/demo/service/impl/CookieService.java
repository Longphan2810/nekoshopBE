package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse resq;

	public Cookie getCookie(String name) {

		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {

			if (cookie.getName().equals(name)) {

				return cookie;
			}
		}

		return null;
	}

	public String getValue(String name) {

		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {

			if (cookie.getName().equals(name)) {

				return cookie.getValue();
			}
		}

		return null;
	}
	
	
	public void add(String name,String value,int hours) {
		
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours*60*60);
		resq.addCookie(cookie);
		
	}
	
	public void remove(String name) {	
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		resq.addCookie(cookie);
	}
	

	
}
