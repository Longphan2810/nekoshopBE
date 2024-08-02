package com.example.demo.request;

import com.example.demo.domain.ProductDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CartRequest {
	
	
	private String emailUser;
	
	private int idProductDetail;
	
	private int idProduct;
	
	private String size;
	
	private int quantity;
	
	
}
