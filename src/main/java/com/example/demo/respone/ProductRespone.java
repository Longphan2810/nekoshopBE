package com.example.demo.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductRespone {
	private Integer idProduct;
	private String name;
	private Float price;
	private Boolean status;
	private Float disscount;
	private String description;
	private Integer idCategory;
	private Integer sizeS;
	private Integer sizeM;
	private Integer sizeL;
	private Integer sizeXL;
	private String nameImage;

}
