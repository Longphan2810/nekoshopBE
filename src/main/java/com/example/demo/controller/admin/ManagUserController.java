package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Users;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.UserServiceImpl;

import jakarta.servlet.ServletContext;

@RestController
@CrossOrigin(originPatterns = {"**"})
@RequestMapping("api")
public class ManagUserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("manageUser")
	public ApiRespone<List<Users>> getListUser(){
		
		Authentication authen =  SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Name : "+authen.getName());
		System.out.println("scope : "+authen.getAuthorities());
		ApiRespone<List<Users>>  response = new ApiRespone<>();
		
		response.setResult(userServiceImpl.findAll());
		return response;
	} 
	@GetMapping("manageUser/{id}")
	public ApiRespone<Users> getListUserById(@PathVariable("id") int id){
		ApiRespone<Users> response = new ApiRespone<>();
		
		response.setResult(userServiceImpl.findById(id).orElseThrow(()->new RuntimeException("USER_NULL")));
		return response;
	} 

}
