package com.example.demo.DTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {

	private int idProduct;
	
	@NotBlank(message = "Nhập tên sản phẩm")
	private String name;
	
	@NotNull(message = "Nhập Giá sản phẩm")
	private float price;
	
	private boolean status;
	private int idCategory;
	private float disscount;
	private String description;
	private Date productDate = new Date();
	private String nameImage;
	private MultipartFile imageFile;
	
	
}
