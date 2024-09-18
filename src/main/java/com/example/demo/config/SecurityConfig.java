package com.example.demo.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${jwt.secretKey}")
	private String secretKey;
	
	private String[] EndPointAdmin = {"/api/manageUser/**","/api/manageOrder/**","/api/manageCategory/**"};
	private String[] EndPointUser = {"/api/locationCostumer/**","/api/shopingCart/**","/api/shopCartCheck/**","/api/manageCategory/**"};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
		http.authorizeHttpRequests(
				(requests) ->  
				requests.requestMatchers(EndPointAdmin).hasAuthority("SCOPE_ADMIN")
				.requestMatchers(HttpMethod.POST,"/api/manageProduct/**").hasAuthority("SCOPE_ADMIN")
				.requestMatchers(HttpMethod.PUT,"/api/manageProduct/**").hasAuthority("SCOPE_ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/manageProduct").permitAll()
				.requestMatchers(EndPointUser).hasAuthority("SCOPE_USER")
				.requestMatchers("/api/user").authenticated()
				.requestMatchers("**").permitAll());
		http.oauth2ResourceServer( oauth2 -> oauth2.jwt(jwtConfig -> jwtConfig.decoder(jwtDecoder())) );
		http.csrf(csrf->csrf.disable());
		
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "SH512");
			
		return 	NimbusJwtDecoder.withSecretKey(secretKeySpec)
				.macAlgorithm(MacAlgorithm.HS512)
				.build();
	}

}
