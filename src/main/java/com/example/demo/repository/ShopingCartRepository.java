package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ShopingCart;
import com.example.demo.domain.Users;

public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer>{

	
	public List<ShopingCart> findAllByUser(Users user);
	
}
