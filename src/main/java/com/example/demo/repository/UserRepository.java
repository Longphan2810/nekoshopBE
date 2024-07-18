package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	public Page<Users> findAllByNameLike(String name,Pageable pageable);
	
	public Page<Users> findAllByNameLikeAndEmailLike(String name,String email,Pageable pageable);

	public Users findByEmail(String email);
	
	public Users findByToken(String token);
	
	
	
}
