package com.example.demo.domain;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity @Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Users {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	@JsonIgnore
	private String password;
	private String email;
	private Date birthday;
	private String phone;
	private Boolean gender;
	
	@Column(columnDefinition = "nvarchar(225) ")
	private String location;
	@Column(columnDefinition = "nvarchar(225) ")
	private String name;
	private boolean role;
	
	@JsonIgnore
	private String token;
	@JsonIgnore
	private Long timeToken;
	
	private String status;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<DeliveryAddress> listDeliveryAddresses;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<ShopingCart> listShopingCart;
	
	@Override
	public String toString() {
		return "Users [ email=" + email + ", name=" + name + ", role=" + role + "]";
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Orders> listOrder;
	
	
}
