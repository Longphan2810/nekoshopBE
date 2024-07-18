package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.DeliveryAddress;
import com.example.demo.domain.Users;
@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryAddress, Integer> {

	
	public List<DeliveryAddress> findAllByUser(Users user);
	
	public DeliveryAddress findById(int id);
	
	public DeliveryAddress findByUserAndStatus(Users user,boolean status);
	
	
}
