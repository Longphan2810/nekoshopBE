package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		System.out.println("+++++++++++++++++++++++");
		return respone;
	}

	@GetMapping("category/{name}")
	public ApiRespone<List<Category>> getCateByName(@PathVariable("name") String name){
		ApiRespone<List<Category>> respone = new ApiRespone<>();
		respone.setResult(categoryService.findAllByNameLike(name));
		System.out.println("+++++++++++++++++++++++");
		return respone;
	}
	
	@PostMapping("manageCategory")
	public ApiRespone<Category> postCate(@RequestBody Category cate){
		ApiRespone<Category> respone = new ApiRespone<>();
		respone.setResult(categoryService.save(cate));
		return respone;
	}
	
	@PutMapping("manageCategory/{id}")
	public ApiRespone<Category> putCate(@RequestBody Category cate,@PathVariable("id") int idCate){
		ApiRespone<Category> respone = new ApiRespone<>();
		System.out.println("check");
		respone.setResult(categoryService.update(cate, idCate));
		return respone;
	
	}
	
	@DeleteMapping("manageCategory/{id}")
	public ApiRespone<Category> deleteCate(@PathVariable("id") int idCate){
		ApiRespone<Category> respone = new ApiRespone<>();
			
		try {
			categoryService.deleteById(idCate);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new RuntimeException("CATE_CAN_NOT_DELETE");
		}
		
		return respone;
	
	}
}
