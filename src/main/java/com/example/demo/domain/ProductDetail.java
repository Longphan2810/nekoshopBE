package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "ProductDetail",uniqueConstraints = @UniqueConstraint(columnNames = {"size","id_Product"}))
public class ProductDetail implements  Serializable {
	
	@Id @Column(name = "id_Product_Detail" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProductDetail;
	private String size;
	private int quality;
	
	
	@ManyToOne()
	@JoinColumn(name = "id_Product")
	private Product product;
	
	@OneToMany(mappedBy = "productDetail")
	private List<OrderDetail> listOrderDetail;
	
}
