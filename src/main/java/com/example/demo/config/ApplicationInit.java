package com.example.demo.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.Users;
import com.example.demo.service.impl.UserServiceImpl;

@Configuration
public class ApplicationInit {

	@Bean
	ApplicationRunner applicationRunner(UserServiceImpl userServiceImpl) {
		
		return args -> {
			if(userServiceImpl.findByEmail("admin")==null) {
				
				PasswordEncoder pe = new BCryptPasswordEncoder(10);
				
				Users admin = new Users();
				admin.setEmail("admin");
				admin.setPassword("admin");
				admin.setStatus("active");
				admin.setRole(true);
				userServiceImpl.save(admin);
			}
			
			
		};
	}
	
	
}
