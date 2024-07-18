package com.example.demo.DTO;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.DeliveryAddress;
import com.example.demo.domain.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
	

	@NotBlank(message = "Vui long nhap name")
	private String name;
	
	
	@NotBlank(message = "Vui long nhap password")
	@Min(value = 8)
	private String password;
	
	@Email(message = "Khong de trong email")
	@NotBlank(message = "Vui  long nhap email")
	private String email;
	
	private boolean role = false;
	
}
