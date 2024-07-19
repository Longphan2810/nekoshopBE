package com.example.demo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class ParamService {

	@Autowired
	HttpServletRequest request;

	public String getString(String name, String defaultValue) {

		String value = request.getParameter(name);

		return value != null ? value : defaultValue;
	}

	public int getInt(String name, int defaultValue) {

		try {
			int value = Integer.parseInt(request.getParameter(name));
			return value;
		} catch (Exception e) {
			// TODO: handle exception
			return defaultValue;
		}

	}

	public double getDouble(String name, double defaultValue) {

		try {
			double value = Double.parseDouble(request.getParameter(name));
			return value;
		} catch (Exception e) {
			// TODO: handle exception
			return defaultValue;
		}
	}

	public boolean getBoolean(String name, boolean defaultValue) {

		try {
			boolean value = Boolean.parseBoolean(request.getParameter(name));
			return value;
		} catch (Exception e) {
			// TODO: handle exception
			return defaultValue;
		}

	}

	public Date getDate(String name, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(request.getParameter(name));
		return date;

	}

}
