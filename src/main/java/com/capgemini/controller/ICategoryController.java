package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.entities.Category;
import com.capgemini.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class ICategoryController {
	
	@Autowired
	ICategoryService service;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category saved=service.addCategory(category);
		return new ResponseEntity<Category>(saved, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<Category> viewCategory(@RequestBody Category category){
		Category showCategory=service.viewCategory(category);
		if(showCategory==null) {
			return new ResponseEntity("Category not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(showCategory, HttpStatus.OK);
	}
	
	@GetMapping(value = "/viewAll")
	public ResponseEntity<List<Category>> viewAllCategory(){
		List<Category> listCategory = service.viewAllCategory();
		if(listCategory == null) {
			return new ResponseEntity("Categories not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Category>>(listCategory, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		Category newCategory=service.updateCategory(category);
		if(newCategory==null) {
			return new ResponseEntity("Category not available for Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(newCategory,HttpStatus.OK); 
	}
	
	@DeleteMapping(value = "/del")
	public ResponseEntity<Category> removeCategory(@RequestBody Category category){
		Category deletedCategory=service.removeCategory(category);
		if(deletedCategory==null) {
			return new ResponseEntity("Category is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(deletedCategory, HttpStatus.OK);
	}
}
