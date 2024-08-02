package com.example.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ShopingCart;
import com.example.demo.domain.Users;
import com.example.demo.respone.ApiRespone;

import com.example.demo.service.impl.CookieService;
import com.example.demo.service.impl.ProductDetailServiceImpl;
import com.example.demo.service.impl.SessionService;
import com.example.demo.service.impl.ShopingCartService;
import com.example.demo.service.impl.UserServiceImpl;



@CrossOrigin(originPatterns = { "**" })
@RestController
@RequestMapping("api")
public class ShopCartController {

	@Autowired
	ShopingCartService shopingCartService;
	
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	SessionService session;

	@Autowired
	CookieService cookieService;

	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;
	
	@GetMapping("/shopingCart/{email}")
	public ApiRespone<List<ShopingCart>> getShopingCart( @PathVariable("email") String email ){
		
		Users user = userServiceImpl.findByEmail(email);
		
		System.out.println(user.getIdUser());
		
		return null;
	}

	
	

}
