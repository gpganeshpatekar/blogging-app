package com.demo.blogging.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Role {
	
	@Id
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Role(Integer id, String name, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}
