package com.example.demo.service.impl;

import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Users;
@Service
public class RegisterService {
	private static final long tenMinuteToMillisecond = 60000 * 10;
	public  void setupToken(Users user) {
		// get current date
		Date date = new Date();
		long finalTimeToken = date.getTime()+ tenMinuteToMillisecond;

		// create token
		String token = Base64.getEncoder().encodeToString(user.toString().getBytes());

		// add status 
		
		String status = "wait";
		
		user.setTimeToken(finalTimeToken);
		user.setToken(token);
		user.setStatus(status);
		
	

	}
	
}
