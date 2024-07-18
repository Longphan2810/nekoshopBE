package com.example.demo.domain;





import jakarta.persistence.Column;
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

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "Delivery_Address")
public class DeliveryAddress {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDeliveryAddress;
	@Column(columnDefinition = "nvarchar(100)")
	private String city;
	@Column(columnDefinition = "nvarchar(100)")
	private String address;
	private Boolean status;
	
	private String phone;
	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	
	@ManyToOne()
	@JoinColumn(name = "id_User")
	private Users user;
	
	
}
