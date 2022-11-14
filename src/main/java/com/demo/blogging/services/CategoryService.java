package com.demo.blogging.services;

import java.util.List;

import com.demo.blogging.payloads.CategoryResponse;
import com.demo.blogging.payloads.CategoryDto;

public interface CategoryService {
	
//	to create category
	CategoryDto createCategory(CategoryDto categoryDto);
//	to get all list of category
	List<CategoryDto> getListOfCategories();
//	to get category by id
	CategoryDto getCategoryById(Integer categoryId);
//	to update category
	CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
//	to delete category
	void deleteCategory(Integer categoryId);
//	get all categories by pagination
	CategoryResponse getCategoriesByPage(Integer pageNumber,Integer pageSize);
	

	
	

}
