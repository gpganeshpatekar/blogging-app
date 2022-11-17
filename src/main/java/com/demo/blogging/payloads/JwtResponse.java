package com.demo.blogging.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class JwtResponse {
	
	private String token;
	private UserDto user;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public JwtResponse(String token, UserDto user) {
		super();
		this.token = token;
		this.user = user;
	}
	
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
