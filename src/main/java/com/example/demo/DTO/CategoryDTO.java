package com.example.demo.DTO;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class CategoryDTO implements Serializable {
	
	private Integer idCategory;
	
	@NotBlank(message = "NAME_CATE_NOTNULL")
	private String name;
	

}