package com.demo.blogging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.blogging.entities.Category;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.payloads.CategoryDto;
import com.demo.blogging.payloads.CategoryResponse;
import com.demo.blogging.repositories.CategoryRepository;
import com.demo.blogging.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

//	to create category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category saveCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

//	to get all list of category
	@Override
	public List<CategoryDto> getListOfCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> categoriesDto = categories.stream().map(c -> this.modelMapper.map(c, CategoryDto.class))
				.collect(Collectors.toList());
		return categoriesDto;
	}

//	to get category by id
	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

//	to update category
	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

//	to delete category
	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepository.delete(category);

	}
	
// 	to get all categories by pagination
	@Override
	public CategoryResponse getCategoriesByPage(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Category> pageCategories = this.categoryRepository.findAll(pageable);
		List<Category> categories = pageCategories.getContent();
		List<CategoryDto> categoriesDto = categories.stream().map((c)-> this.modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setContent(categoriesDto);
		categoryResponse.setPageNumber(pageCategories.getNumber());
		categoryResponse.setPageSize(pageCategories.getSize());
		categoryResponse.setTotalElements(pageCategories.getTotalElements());
		categoryResponse.setTotalPage(pageCategories.getTotalPages());
		categoryResponse.setLastPage(pageCategories.isLast());
		
		return categoryResponse;
	}

}
