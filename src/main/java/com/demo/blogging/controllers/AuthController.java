package com.demo.blogging.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blogging.security.JwtHelper;
import com.demo.blogging.services.impl.CustomUserDetailService;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.payloads.JwtRequest;
import com.demo.blogging.payloads.JwtResponse;
import com.demo.blogging.payloads.UserDto;

@RestController
@RequestMapping("blogging-app/auth/")
public class AuthController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {

//		authenticate
		this.authenticateUser(request.getUsername(), request.getPassword());
//		if authenticate then.. we have to create authenticationManager bean in security configuration file..
		UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());

		String token = this.helper.generateToken(userDetails);
		
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setToken(token);
		jwtResponse.setUser(this.modelMapper.map(userDetails, UserDto.class));
		
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}

	private void authenticateUser(String username, String password) throws Exception {

		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new ResourceNotFoundException("Invalid Credentials. Please Check Your Username And Password.");
		} catch (DisabledException e) {
			throw new Exception("User Is Not Active..");
		}

	}

	
	

}
