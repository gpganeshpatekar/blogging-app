package com.demo.blogging;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.blogging.entities.Role;
import com.demo.blogging.repositories.RoleRepository;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passowrdEncoder;


	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role1 = new Role();
			role1.setId(5001);
			role1.setName("ROLE_ADMIN");
			
			Role role2 = new Role();
			role2.setId(5002);
			role2.setName("ROLE_NORMAL");
			
			Role role3 = new Role();
			role3.setId(5003);
			role3.setName("ROLE_STAFF");
			List<com.demo.blogging.entities.Role> roles = new ArrayList<>();
			roles.add(role1);
			roles.add(role2);
			roles.add(role3);
			this.roleRepository.saveAll(roles);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
