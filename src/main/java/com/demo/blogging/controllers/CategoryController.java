package com.demo.blogging.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.payloads.CategoryResponse;
import com.demo.blogging.payloads.ApiResponse;
import com.demo.blogging.payloads.CategoryDto;
import com.demo.blogging.services.CategoryService;

@RestController
@RequestMapping("blogging-app/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

//	to create category
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

//	to get all list of category
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categories = this.categoryService.getListOfCategories();
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
	
//  to get all categories by pagination	
	@GetMapping(value = "/page",produces = "application/json")
	public ResponseEntity<CategoryResponse> getCategories(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize
			){
		CategoryResponse categoryResponse = this.categoryService.getCategoriesByPage(pageNumber,pageSize);
		return new ResponseEntity<CategoryResponse>(categoryResponse,HttpStatus.OK);
	}
	
	
//	to get category by id
	@GetMapping(value = "/{categoryId}", produces = "application/json")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}

//	to update category
	@PutMapping(value = "/{categoryId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer categoryId,
			@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryId, categoryDto);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
//	to delete category
	@DeleteMapping(value = "/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.DELETE_CATEGORY ,true),HttpStatus.OK);
	}
}
