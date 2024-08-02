package com.example.demo.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.ProductServiceImpl;

@RestController
@CrossOrigin(originPatterns = {"**"})
@RequestMapping("api")
public class ShopController {

	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@GetMapping("/shop")
	public ApiRespone<Page<Product>> getShopPage() {

		ApiRespone<Page<Product>> respone = new ApiRespone<>();
		
		Pageable page = PageRequest.of(0, 9);

		Page<Product> listProduct = productServiceImpl.findAll(page);
		
		respone.setResult(listProduct);
		
		return respone;

	}
	
	
	@GetMapping("/shop/filter")
	public ApiRespone<Page<Product>> getShopAndFilter( @RequestParam("p") Optional<Integer> p,
			@RequestParam("sortBy") Optional<String> sortBy,@RequestParam("keywords") Optional<String> keywords,
			@RequestParam("filterByPrice") Optional<String> filterByPrice) {

		ApiRespone<Page<Product>> respone = new ApiRespone<>();
		
		Direction direc = null;
		
		Page<Product> listProduct = null;

		String nameSortBy = sortBy.orElse("price low to high");
		String keywordsSearch = keywords.orElse("");

		switch (nameSortBy) {
		case "price low to high": {
			nameSortBy = "price low to high";
			direc = Direction.ASC;
			break;
		}
		case "price high to low": {
			nameSortBy = "price high to low";
			direc = Direction.DESC;
			break;
		}
		default: {
			nameSortBy = "price low to high";
			direc = Direction.ASC;
			break;
		}
		}

		System.out.println(sortBy.orElse("???xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
		
		float minPrice = Float.MIN_VALUE;
		float maxPrice = Float.MAX_VALUE;

		switch (filterByPrice.orElse("all")) {
		case "50": {
			minPrice = 0;
			maxPrice = 50000;
			break;
		}

		case "100": {
			minPrice = 50000;
			maxPrice = 100000;
			break;
		}

		case "200": {
			minPrice = 100000;
			maxPrice = 200000;
			break;
		}
		case "200+": {
			minPrice = 200000;
			maxPrice = Float.MAX_VALUE;
			break;
		}

		default: {
			minPrice = Float.MIN_VALUE;
			maxPrice = Float.MAX_VALUE;
			break;
		}

		}
		

		Sort sort = Sort.by(direc, "price");

		Pageable page = PageRequest.of(p.orElse(0), 9, sort);

		
		if(keywordsSearch.trim().equalsIgnoreCase("")) {
		// get page without  keyword
		listProduct = productServiceImpl.findAllByPriceBetween(minPrice, maxPrice, page);
		
		} else {
		 listProduct = productServiceImpl.findAllByPriceBetweenAndNameLike(minPrice, maxPrice, "%"+keywordsSearch+"%", page);
		}
	
		respone.setResult(listProduct);
		
		return respone;

	}
	
	
	@GetMapping("/shop/{category}")
	public ApiRespone<Page<Product>> getShopByCategory( @PathVariable("category") String cateName) {

		ApiRespone<Page<Product>> respone = new ApiRespone<>();
		
		Category cate = categoryServiceImpl.findByNameLike(cateName);

		Pageable page = PageRequest.of(0, 9);

		Page<Product> listProduct = productServiceImpl.findAllByCategory(cate, page);

		

		System.out.println("Day");
		System.out.println(cateName);
		System.out.println(cate.getName());
	
		respone.setResult(listProduct);	
		return respone;

	}
	
	@GetMapping("/shop/{category}/filter")
	public ApiRespone<Page<Product>> getShopByCategoryAndFilter( @PathVariable("category") String cateName,
			@RequestParam("p") Optional<Integer> p, @RequestParam("sortBy") Optional<String> sortBy,
			@RequestParam("keywords") Optional<String> keywords,
			@RequestParam("filterByPrice") Optional<String> filterByPrice) {
		
		ApiRespone<Page<Product>>respone = new ApiRespone<>();
		System.out.println("=========================================");
		System.out.println(p.orElse(0));
		
		
		Direction direc = null;
		String nameSortBy = sortBy.orElse("price low to high");
		String keywordsSearch = keywords.orElse("");

		switch (nameSortBy) {
		case "price low to high": {
			nameSortBy = "price low to high";
			direc = Direction.ASC;
			break;
		}
		case "price high to low": {
			nameSortBy = "price high to low";
			direc = Direction.DESC;
			break;
		}
		default: {
			nameSortBy = "price low to high";
			direc = Direction.ASC;
			break;
		}
		}

		float minPrice = Float.MIN_VALUE;
		float maxPrice = Float.MAX_VALUE;

		switch (filterByPrice.orElse("all")) {
		case "50": {
			minPrice = 0;
			maxPrice = 50000;
			break;
		}

		case "100": {
			minPrice = 50000;
			maxPrice = 100000;
			break;
		}

		case "200": {
			minPrice = 100000;
			maxPrice = 200000;
			break;
		}
		case "200+": {
			minPrice = 200000;
			maxPrice = Float.MAX_VALUE;
			break;
		}
		default: {
			minPrice = Float.MIN_VALUE;
			maxPrice = Float.MAX_VALUE;
			break;
		}

		}

		Sort sort = Sort.by(direc, "price");

		Pageable page = PageRequest.of(p.orElse(0), 9, sort);

		Category cate = categoryServiceImpl.findByNameLike(cateName);

		Page<Product> listProduct ;

		if(keywordsSearch.trim().equalsIgnoreCase("")) {
			// get page without  keyword
			listProduct =  productServiceImpl.findAllByCategoryAndPriceBetween(cate, minPrice, maxPrice, page);
			
			} else {
			 listProduct = productServiceImpl.findAllByCategoryAndPriceBetweenAndNameLike(cate, minPrice, maxPrice, "%"+keywordsSearch+"%", page);
			}
		
		
		listProduct.and(listProduct);

		for (Product product : listProduct) {
			System.out.println(product.getName());
		}

		respone.setResult(listProduct);
		
		return respone;

	}
	
}
