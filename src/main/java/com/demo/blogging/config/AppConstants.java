package com.demo.blogging.config;

public class AppConstants {

	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "5";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "asc";
	
	// Role
	public static final Integer NORMAL_USER = 5002;
	
	// security
	public static String[] PUBLIC_URLS = { 
												  "/blogging-app/auth/**",
												  "/blogging-app/users/reg/"
	};

}
