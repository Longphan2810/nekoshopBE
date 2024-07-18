package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public  List<Category> findAllByNameLike(String name);
	
	public Category findByNameLike(String name);
	
	
}
