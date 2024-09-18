package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Users;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.DeliveryAddressServiceImpl;
import com.example.demo.service.impl.OrderDetailServiceImpl;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.service.impl.ProductDetailServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
@CrossOrigin(originPatterns = {"**"})
@RestController
@RequestMapping("api")
public class ManageOrderController {
	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;

	@Autowired
	OrderServiceImpl orderServiceImpl;

	@Autowired
	OrderDetailServiceImpl orderDetailServiceImpl;

	@Autowired
	DeliveryAddressServiceImpl deliveryAddressServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/manageOrder")
	public ApiRespone<List<Orders>> getListOrder(){
		ApiRespone<List<Orders>> response = new ApiRespone<>();
		
		response.setResult(orderServiceImpl.findAll());
		
		return response;
	}
	@GetMapping("/manageOrder/{id}")
	public ApiRespone<List<Orders>>  getOrder(@PathVariable("id") int id){
		ApiRespone<List<Orders>>  response = new ApiRespone<>();
		
		List<Orders> listResponse = new ArrayList<>();
		listResponse.add(orderServiceImpl.findById(id).orElse(null));
		response.setResult(listResponse);
		
		return response;
	}
	
	
	
}
