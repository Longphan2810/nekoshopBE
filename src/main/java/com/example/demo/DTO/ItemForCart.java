package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ItemForCart {
	private int idProduct;
	private String name;
	private float price;
	private String nameImage;
	private int idProductDetail;
	private String size;
	private int quality;
}
