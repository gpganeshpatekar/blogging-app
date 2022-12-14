package com.demo.blogging.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.demo.blogging.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4, max = 20, message = "name must be min of 4 characters and max of 20 characters")
	private String name;
	@Email
	@Pattern(regexp = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+",message = "please enter valid email id.")
	private String email;
	@NotEmpty
	@Size(min = 6,message = "password must be min of 6 characters")
	private String password;
	@NotEmpty
	private String about;
	private boolean active = true;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	public UserDto(int id, String name, String email, String password, String about, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.active = active;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore // password won't get
	public String getPassword() {
		return password;
	}
	@JsonProperty // but you can set password
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	
	
	
	
	
	

}
