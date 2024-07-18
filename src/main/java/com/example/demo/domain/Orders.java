package com.example.demo.domain;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table
public class Orders {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;
	private String  status;
	private float amount;
	private  String paymentMethod;
	@Column(columnDefinition = "nvarchar(225) ")
	private String name;
	@Column(columnDefinition = "nvarchar(225) ")
	private String address;
	@Column(columnDefinition = "nvarchar(225) ")
	private String city;
	@Column(name = "phone")
	private String phone;
	
	private String transactionCode;
	private Boolean payedVNpay;
	private Long countDownTimeTranVNpay;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate = new Date();

	@ManyToOne()
	@JoinColumn(name = "id_user")
	private Users user;
	
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> listOrderDetails;
	
}
