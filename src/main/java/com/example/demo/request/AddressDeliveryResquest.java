package com.example.demo.request;

import com.example.demo.domain.Users;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AddressDeliveryResquest {

	private String city;
	private String address;
	private Boolean status;
	private String phone;
	private String name;
	private String mailUserCurrent;
	
	
}
