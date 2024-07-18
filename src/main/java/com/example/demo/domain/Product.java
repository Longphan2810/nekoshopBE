package com.example.demo.domain;

import java.io.Serializable;
import java.util.Date;
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
@Entity @Table(name = "Products",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Product  implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	@Column(name = "name", columnDefinition = " nvarchar(225) not null")
	private String name;
	private float price;
	private boolean status;
	private float disscount;
	
	private String description;
	private Date productDate;
	private String nameImage;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	

	@OneToMany(mappedBy = "product")
	private List<ProductDetail> listProductDetails;
	
}
