package com.example.demo.DTO;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.DeliveryAddress;
import com.example.demo.domain.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
	

	
	private String name;
	
	
	@NotBlank(message = "PASS_INVALID")
	@Size(min = 8, message = "PASS_INVALID")
	private String password;
	
	private String confirmPassword;
	
	@Email(message = "MAIL_INVALID")
	@NotBlank(message = "MAIL_NULL")
	private String email;
	
	private boolean role = false;
	
}
