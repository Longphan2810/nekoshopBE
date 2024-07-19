package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductDetail;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.service.ProductDetailServiceInterface;

@Service
public class ProductDetailServiceImpl implements ProductDetailServiceInterface {

	
	ProductDetailRepository productDetailRepository;

	public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
		
		this.productDetailRepository = productDetailRepository;
	}

	@Override
	public <S extends ProductDetail> S save(S entity) {
		return productDetailRepository.save(entity);
	}

	@Override
	public List<ProductDetail> findAll(Sort sort) {
		return productDetailRepository.findAll(sort);
	}

	@Override
	public List<ProductDetail> findAll() {
		return productDetailRepository.findAll();
	}

	@Override
	public <S extends ProductDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDetailRepository.findAll(example, pageable);
	}

	@Override
	public Optional<ProductDetail> findById(Integer id) {
		return productDetailRepository.findById(id);
	}

	@Override
	public <S extends ProductDetail> boolean exists(Example<S> example) {
		return productDetailRepository.exists(example);
	}

	@Override
	public long count() {
		return productDetailRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		productDetailRepository.deleteById(id);
	}

	public ProductDetail findBySizeAndIdProduct(String size, int idProduct) {
		return productDetailRepository.findBySizeAndIdProduct(size, idProduct);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ProductDetail getById(Integer id) {
		return productDetailRepository.getById(id);
	}

	@Override
	public void delete(ProductDetail entity) {
		productDetailRepository.delete(entity);
	}

	public void DeleteAllByIdProduct(int idProduct) {
		productDetailRepository.DeleteAllByIdProduct(idProduct);
	}

	public ProductDetail findByProductAndSize(Product product, String size) {
		return productDetailRepository.findByProductAndSize(product, size);
	}

	public ProductDetail findById(int id) {
		return productDetailRepository.findById(id);
	}

	public List<ProductDetail> findAllByProduct(Product product) {
		return productDetailRepository.findAllByProduct(product);
	}
	
	
	
}
