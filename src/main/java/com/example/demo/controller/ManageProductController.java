package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.domain.Product;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.FileService;
import com.example.demo.service.impl.ParamService;
import com.example.demo.service.impl.ProductDetailServiceImpl;
import com.example.demo.service.impl.ProductServiceImpl;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(originPatterns = {"**"})
@RequestMapping("api")
public class ManageProductController {

	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;

	@Autowired
	ServletContext application;

	@Autowired
	FileService fileService;

	@Autowired
	ParamService paramService;

	@GetMapping("/manageProduct")
	public ApiRespone<List<Product>> getListProduct() {
		ApiRespone<List<Product>> respone = new ApiRespone<List<Product>>();

		respone.setResult(productServiceImpl.findAll());

		return respone;
	}

	@GetMapping("/manageProduct/{id}")
	public ApiRespone<Product> getProduct(@PathVariable("id") int id) {
		ApiRespone<Product> respone = new ApiRespone<Product>();

		respone.setResult(productServiceImpl.getById(id));

		return respone;
	}

	@PostMapping("/manageProduct")
	public ApiRespone<Product> postProduct(@ModelAttribute @Valid ProductDTO productDto) {

		if (productDto.getFile() != null) {
			System.out.println(productDto.getFile().getOriginalFilename());
		}
		
		Product product = new Product();
		
//		BeanUtils.copyProperties(productDto, product);
		

		System.out.println(productDto.toString());
		System.out.println(product.toString());
		
		
		

		return null;
	}

}
