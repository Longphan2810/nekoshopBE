package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.ProductDetail;

public interface ProductDetailServiceInterface {

	void delete(ProductDetail entity);

	ProductDetail getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends ProductDetail> boolean exists(Example<S> example);

	Optional<ProductDetail> findById(Integer id);

	<S extends ProductDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	List<ProductDetail> findAll();

	List<ProductDetail> findAll(Sort sort);

	<S extends ProductDetail> S save(S entity);

}
