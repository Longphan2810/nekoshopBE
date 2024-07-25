package com.example.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "Products",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))

public class Product  implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	
	@Column(columnDefinition = " nvarchar(225) not null")
	@NotBlank(message = "NAME_PRODUCT_NULL")
	private String name;	
	@Min(value = 0 , message = "PRICE_PRODUCT_NOT_NEGATIVE")
	@NotNull(message = "PRICE_PRODUCT_NULL")
	private float price;
	@NotNull(message = "STATUS_PRODUCT_NOT_NULL")
	private boolean status;
	@Min(value = 0 , message = "DISSCOUNT_PRODUCT_NOT_NEGATIVE")
	@NotNull(message = "DISSCOUNT_PRODUCT_NULL")
	private float disscount;
	private String description;
	
	@DateTimeFormat
	private Date productDate = new Date();
	private String nameImage;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductDetail> listProductDetails;

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", name=" + name + ", price=" + price + ", status=" + status
				+ ", disscount=" + disscount + ", description=" + description + ", productDate=" + productDate
				+ ", nameImage=" + nameImage + "]";
	}
	
	
	
}
