package com.example.demo.DTO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

//	@NotBlank(message = "NAME_PRODUCT_NULL")
	private String name;
//	@Min(value = 0, message = "PRICE_PRODUCT_NOT_NEGATIVE")
//	@NotNull(message = "PRICE_PRODUCT_NULL")
	private Float price;
//	@NotNull(message = "STATUS_PRODUCT_NOT_NULL")
	private Boolean status;
//	@Min(value = 0, message = "DISSCOUNT_PRODUCT_NOT_NEGATIVE")
//	@NotNull(message = "DISSCOUNT_PRODUCT_NULL")
	private Float disscount;
	private String description;
//	@NotNull(message = "ID_CATE_PRODUCT_NULL")
//	@Min( value = 1,message = "ID_CATE_PRODUCT_MIN")
	private Integer idCategory;
	private Integer sizeS;
	private Integer sizeM;
	private Integer sizeL;
	private Integer sizeXL;
	private MultipartFile file;

}
