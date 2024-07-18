package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class OrderDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderDetail;

	private int quanlity;
	private float price;
	
	@ManyToOne()
	@JoinColumn(name = "id_order")
	private Orders order;
	@ManyToOne()
	@JoinColumn(name = "id_productDetail")
	private ProductDetail productDetail;
	
}
