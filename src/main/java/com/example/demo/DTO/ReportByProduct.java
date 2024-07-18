package com.example.demo.DTO;

import java.io.Serializable;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductDetail;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @NoArgsConstructor
@Entity
public class ReportByProduct implements Serializable {
	@Id
	private ProductDetail productDetail;
	private float quatitySold;
	public ReportByProduct(ProductDetail product, float quatitySold) {
		
		this.productDetail = product;
		this.quatitySold = quatitySold;
	}
	
	
	
}
