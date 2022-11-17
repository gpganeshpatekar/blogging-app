package com.demo.blogging.payloads;

import java.util.HashSet;
import java.util.Set;

import com.demo.blogging.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RoleDto {
	
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
