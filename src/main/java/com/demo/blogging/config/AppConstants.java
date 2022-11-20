package com.demo.blogging.config;

public class AppConstants {

	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "5";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "asc";
	
	public static final String DELETE_USER = "User Deleted Successfully..";
	public static final String DELETE_CATEGORY = "Category Deleted Successfully..";
	public static final String DELETE_POST = "Post Deleted Successfully..";
	public static final String DELETE_COMMENT = "Comment Deleted Successfully..";
	
	public static final String IMAGE_PATH =  "${project.image}";
	
	
	
	
//  role
	public static final Integer NORMAL_USER = 5002;
	
//  security
//	com.demo.blogging.services.impl.CustomUserDetailService
	public static final String USER_NOT_FOUND = "User Not Found With The Username : ";
//	
	public static String[] PUBLIC_URLS = { 
												  "/blogging-app/auth/**",
												  "/blogging-app/users/reg/",
												  "/blogging-app/posts/**",
												  "/v2/api-docs/**",
												  "/v3/api-docs/**",
												  "/swagger-resources/**",
												  "/swagger-ui/**",
												  "/webjars/**"
	};

}
