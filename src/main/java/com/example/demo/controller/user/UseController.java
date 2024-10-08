package com.example.demo.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Users;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.UserResponse;
import com.example.demo.service.impl.UserServiceImpl;

@CrossOrigin(originPatterns = {"**"})
@RestController
@RequestMapping("api")
public class UseController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("user/{email}")
	public ApiRespone<UserResponse> getUserByMail(@PathVariable("email") String email) {

		ApiRespone<UserResponse> response = new ApiRespone<>();
		Users user = userServiceImpl.findByEmail(email);

		System.out.println(user.toString());

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(user, userResponse);

		response.setResult(userResponse);

		return response;
	}

	@PostMapping("user")
	public ApiRespone<UserResponse> postUser(@RequestBody UserResponse userResponse) {

		ApiRespone<UserResponse> response = new ApiRespone<>();
		
		System.out.println(userResponse.toString());

		Users user = userServiceImpl.findByEmail(userResponse.getEmail());
		
		BeanUtils.copyProperties( userResponse,user);
		
		userServiceImpl.update(user);
	
		response.setResult(userResponse);

		return response;
	}

}
