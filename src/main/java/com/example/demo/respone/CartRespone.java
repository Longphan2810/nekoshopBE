package com.example.demo.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data @NoArgsConstructor
public class CartRespone {

	private int  idProductDetail;
	private String name ;
	private String size;
	private float price;
	private  int quantity;
	private String imgName;
}
