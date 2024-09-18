package com.example.demo.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderDetailResponse {

	private String  phone;
	private String name;
	private String status;
	private String address;
	private String city;
	private String  nameProduct;
	private String  size;
	private String imgProduct;
	private int quantity;
	private float price;
	
	
}
