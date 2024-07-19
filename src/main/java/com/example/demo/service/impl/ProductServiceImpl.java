package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductServiceInterface;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

	ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
	
		this.productRepository = productRepository;
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAllById(Iterable<Integer> ids) {
		return productRepository.findAllById(ids);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepository.findAll(example, pageable);
	}

	@Override
	public boolean existsById(Integer id) {
		return productRepository.existsById(id);
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return productRepository.exists(example);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.findById(id).orElseThrow(()-> new RuntimeException("PRODUCT_NULL"));
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	public Page<Product> findAllByNameLike(String name, Pageable pageable) {
		return productRepository.findAllByNameLike(name, pageable);
	}

	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<Product> findAllByCategory(Category category, Pageable pageable) {
		return productRepository.findAllByCategory(category, pageable);
	}

	public Page<Product> findAllByPriceBetween(float minPrice, float maxPrice, Pageable pageable) {
		return productRepository.findAllByPriceBetween(minPrice, maxPrice, pageable);
	}

	public Page<Product> findAllByCategoryAndPriceBetween(Category category, float minPrice, float maxPrice,
			Pageable pageable) {
		return productRepository.findAllByCategoryAndPriceBetween(category, minPrice, maxPrice, pageable);
	}

	public Page<Product> findAllByPriceBetweenAndNameLike(float minPrice, float maxPrice, String name,
			Pageable pageable) {
		return productRepository.findAllByPriceBetweenAndNameLike(minPrice, maxPrice, name, pageable);
	}

	public Page<Product> findAllByCategoryAndPriceBetweenAndNameLike(Category category, float minPrice, float maxPrice,
			String name, Pageable pageable) {
		return productRepository.findAllByCategoryAndPriceBetweenAndNameLike(category, minPrice, maxPrice, name,
				pageable);
	}
	
	
	
}
