package com.example.demo.respone;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class UserResponse {
	private String email;
	private Date birthday;
	private String phone;
	private Boolean gender;
	private String location;
	private String name;
	private boolean role;
	
}
