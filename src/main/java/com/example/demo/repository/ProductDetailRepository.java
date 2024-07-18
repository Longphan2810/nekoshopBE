package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductDetail;

import jakarta.transaction.Transactional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

	@Query("Select o from ProductDetail o where o.size like ?1 and o.product.idProduct = ?2")
	public ProductDetail findBySizeAndIdProduct(String size,int idProduct);
	
	@Modifying
	@Transactional
	@Query("Delete from ProductDetail o where o.product.idProduct = ?1")
	public void DeleteAllByIdProduct(int idProduct);
	
	public ProductDetail findByProductAndSize(Product product,String size);
	
	public List<ProductDetail> findAllByProduct(Product product);
	
	public ProductDetail findById(int id);
	
	
}
