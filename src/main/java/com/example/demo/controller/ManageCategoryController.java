package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Category;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.CategoryServiceImpl;

@RestController
@CrossOrigin(originPatterns = {"**"})
@RequestMapping("api")
public class ManageCategoryController {

	@Autowired
	 CategoryServiceImpl categoryService;
	
	@GetMapping("category")
	public ApiRespone<List<Category>> getListCate(){
		ApiRespone<List<Category>> respone = new ApiRespone<>();
		respone.setResult(categoryService.findAll());
		return respone;
	}

	@GetMapping("category/{name}")
	public ApiRespone<List<Category>> getCateByName(@PathVariable("name") String name){
		ApiRespone<List<Category>> respone = new ApiRespone<>();
		respone.setResult(categoryService.findAllByNameLike(name));
		return respone;
	}
	
	@PostMapping("category")
	public ApiRespone<Category> postCate(@RequestBody Category cate){
		ApiRespone<Category> respone = new ApiRespone<>();
		respone.setResult(categoryService.save(cate));
		return respone;
	}
	
	@PutMapping("category/{id}")
	public ApiRespone<Category> putCate(@RequestBody Category cate,@PathVariable("id") int idCate){
		ApiRespone<Category> respone = new ApiRespone<>();
		System.out.println("check");
		respone.setResult(categoryService.update(cate, idCate));
		return respone;
	
	}
	
	@DeleteMapping("category/{id}")
	public ApiRespone<Category> deleteCate(@PathVariable("id") int idCate){
		ApiRespone<Category> respone = new ApiRespone<>();
		categoryService.deleteById(idCate);
		return respone;
	
	}
}
