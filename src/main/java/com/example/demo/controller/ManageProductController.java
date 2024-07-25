package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.domain.ProductDetail;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.ProductRespone;
import com.example.demo.service.impl.CategoryServiceImpl;
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
	CategoryServiceImpl categoryServiceImpl;

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
	
	@GetMapping("/manageProduct/findByName/{name}")
	public ApiRespone<List<Product>> getListProductByName(@PathVariable("name") String name) {
		ApiRespone<List<Product>> respone = new ApiRespone<List<Product>>();
		PageRequest pageable = PageRequest.of(0, 999);
		respone.setResult(productServiceImpl.findAllByNameLike("%"+name+"%", pageable).getContent());

		return respone;
	}

	@GetMapping("/manageProduct/{id}")
	public ApiRespone<ProductRespone> getProduct(@PathVariable("id") int id) {
		ApiRespone<ProductRespone> respone = new ApiRespone<ProductRespone>();
		ProductRespone productRespone = new ProductRespone();
		
		Product productGotById = productServiceImpl.getById(id);
		ProductDetail sizeS = productDetailServiceImpl.findByProductAndSize(productGotById, "SizeS");
		ProductDetail sizeM = productDetailServiceImpl.findByProductAndSize(productGotById, "SizeM");
		ProductDetail sizeL = productDetailServiceImpl.findByProductAndSize(productGotById, "SizeL");
		ProductDetail sizeXL = productDetailServiceImpl.findByProductAndSize(productGotById, "SizeXL");
		
		BeanUtils.copyProperties(productGotById, productRespone);
		productRespone.setIdCategory(productGotById.getCategory().getIdCategory());
		
		try {
			productRespone.setSizeS(sizeS.getQuality());
			productRespone.setSizeM(sizeM.getQuality());
			productRespone.setSizeL(sizeL.getQuality());
			productRespone.setSizeXL(sizeXL.getQuality());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("PRODUCT_DETAIL_NULL");
		}
		
		
		respone.setResult(productRespone);

		return respone;
	}

	@PostMapping("/manageProduct")
	public ApiRespone<Product> postProduct(@ModelAttribute @Valid ProductDTO productDto,@RequestParam(name = "file",required = false)  MultipartFile file) {

		ApiRespone<Product> respone = new ApiRespone<>();
		Product product = productServiceImpl.findByName(productDto.getName());
		
		if(product!=null) {
			throw new RuntimeException("NAME_PRODUCT_ALREADY_EXISTS");
		} else  {
			product = new Product();
		}
		BeanUtils.copyProperties(productDto, product);
		
		Category cateSelected = categoryServiceImpl.getById(productDto.getIdCategory());
		
		if (file != null) {
			System.out.println(file.getOriginalFilename());
			product.setNameImage(file.getOriginalFilename());
			fileService.saveFile(file);
		}
		
		product.setCategory(cateSelected);
		
		productServiceImpl.save(product);
		
		ProductDetail productSizeS = new ProductDetail();
		productSizeS.setProduct(product);
		productSizeS.setSize("SizeS");
		productSizeS.setQuality(productDto.getSizeS());
		
		ProductDetail productSizeM = new ProductDetail();
		productSizeM.setProduct(product);
		productSizeM.setSize("SizeM");
		productSizeM.setQuality(productDto.getSizeM());
		
		ProductDetail productSizeL = new ProductDetail();
		productSizeL.setProduct(product);
		productSizeL.setSize("SizeL");
		productSizeL.setQuality(productDto.getSizeL());
		
		ProductDetail productSizeXL = new ProductDetail();
		productSizeXL.setProduct(product);
		productSizeXL.setSize("SizeXL");
		productSizeXL.setQuality(productDto.getSizeXL());
		
		productDetailServiceImpl.save(productSizeS);
		productDetailServiceImpl.save(productSizeM);
		productDetailServiceImpl.save(productSizeL);
		productDetailServiceImpl.save(productSizeXL);
		
	
		
		respone.setResult(product);

		return respone;
	}
	
	
	@PutMapping("/manageProduct/{id}")
	public ApiRespone<Product> putProduct(@PathVariable("id") int idProduct,@ModelAttribute @Valid ProductDTO productDto,@RequestParam(name = "file",required = false)  MultipartFile file) {
		ApiRespone<Product> respone = new ApiRespone<>();
		 
		 Product product = productServiceImpl.getById(idProduct);
		 if(product==null) {
				throw new RuntimeException("PRODUCT_NULL");
			}
		BeanUtils.copyProperties(productDto, product);
		
		Category cateSelected = categoryServiceImpl.getById(productDto.getIdCategory());
		
		if (file != null) {
			System.out.println(file.getOriginalFilename());
			product.setNameImage(file.getOriginalFilename());
			fileService.saveFile(file);
		}
		
		product.setCategory(cateSelected);
		
		productServiceImpl.save(product);
		
		ProductDetail productSizeS = productDetailServiceImpl.findBySizeAndIdProduct("SizeS", product.getIdProduct());
		productSizeS.setProduct(product);
		productSizeS.setSize("SizeS");
		productSizeS.setQuality(productDto.getSizeS());
		
		ProductDetail productSizeM = productDetailServiceImpl.findBySizeAndIdProduct("SizeM", product.getIdProduct());
		productSizeM.setProduct(product);
		productSizeM.setSize("SizeM");
		productSizeM.setQuality(productDto.getSizeM());
		
		ProductDetail productSizeL = productDetailServiceImpl.findBySizeAndIdProduct("SizeL", product.getIdProduct());
		productSizeL.setProduct(product);
		productSizeL.setSize("SizeL");
		productSizeL.setQuality(productDto.getSizeL());
		
		ProductDetail productSizeXL = productDetailServiceImpl.findBySizeAndIdProduct("SizeXL", product.getIdProduct());
		productSizeXL.setProduct(product);
		productSizeXL.setSize("SizeXL");
		productSizeXL.setQuality(productDto.getSizeXL());
		
		productDetailServiceImpl.save(productSizeS);
		productDetailServiceImpl.save(productSizeM);
		productDetailServiceImpl.save(productSizeL);
		productDetailServiceImpl.save(productSizeXL);
		
		System.out.println(productDto.toString());
		System.out.println(product.toString());
		
		respone.setResult(product);

		return respone;
			
		
	}

}
