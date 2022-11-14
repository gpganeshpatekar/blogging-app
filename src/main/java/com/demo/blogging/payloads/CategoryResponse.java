package com.demo.blogging.payloads;

import java.util.List;

public class CategoryResponse {
	
	private List<CategoryDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPage;
	private boolean lastPage;
	
	public CategoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CategoryDto> getContent() {
		return content;
	}

	public void setContent(List<CategoryDto> content) {
		this.content = content;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	
	
	
	

}
