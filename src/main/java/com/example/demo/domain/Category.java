package com.example.demo.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "Categories",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;
	@Column(name = "name",columnDefinition = "nvarchar(255) not null ")
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Product> listProduct;

	@Override
	public String toString() {
		return  name ;
	}

	
	
}
