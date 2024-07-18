package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.Category;

public interface CategoryServiceInterface {

	void deleteAll();

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	void delete(Category entity);

	long count();


	<S extends Category> boolean exists(Example<S> example);

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	Optional<Category> findById(Integer id);

	List<Category> findAllByNameLike(String name);

}
