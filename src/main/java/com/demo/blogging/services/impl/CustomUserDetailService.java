package com.demo.blogging.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.entities.User;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepoository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from db by username
		User user = this.userRepoository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND+username));
		return user;
	}

}
