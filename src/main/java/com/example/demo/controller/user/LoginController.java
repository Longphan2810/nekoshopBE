package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.AuthenRequest;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.AuthenRespone;
import com.example.demo.service.impl.AuthenService;
import com.example.demo.service.impl.CookieService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(originPatterns = "**", allowCredentials = "true")
@RestController
@RequestMapping("api")
public class LoginController {

	@Autowired
	AuthenService authenService;

	@Autowired
	CookieService cookieService;

	@PostMapping("/login")
	public ApiRespone<AuthenRespone> postLogin(@RequestBody AuthenRequest authRequest) {
		ApiRespone<AuthenRespone> respone = authenService.authenAccount(authRequest);

	
		return respone;
	}

}
