package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.Product;

public interface ProductServiceInterface {

	void delete(Product entity);

	Product getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Product> boolean exists(Example<S> example);

	boolean existsById(Integer id);

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Product> findAllById(Iterable<Integer> ids);

	List<Product> findAll();

	List<Product> findAll(Sort sort);

	<S extends Product> S save(S entity);

}
