package com.demo.blogging.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blogging.payloads.ApiResponse;
import com.demo.blogging.payloads.UserDto;
import com.demo.blogging.services.UserService;

@RestController
@RequestMapping("blogging-app/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
//	to create user
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}
	
//	to get all list of users
	@GetMapping(value = "/all",produces = "application/json")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> users = this.userService.getListOfUsers();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
//	to get all list of active users	
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<UserDto>> getAllActiveUser(){
		List<UserDto> activeUsers = this.userService.getListOfActiveUsers();
		return new ResponseEntity<List<UserDto>>(activeUsers,HttpStatus.OK);
	}
	
//	to get a user by user id
	@GetMapping(value = "/{id}",produces = "application/json")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {

		UserDto user = this.userService.getUser(id);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
//	to update user
	@PutMapping(value = "/{id}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer id,@Valid @RequestBody UserDto userDto) {
		UserDto updateUser = this.userService.updateUser(id, userDto);
		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
	}
//	to deactivate user (soft delete)
	@PutMapping(value = "/delete/{id}")
	public ResponseEntity<ApiResponse> deactivateUser(@PathVariable Integer id,@Valid @RequestBody UserDto userDto) {
		userDto.setName(userDto.getName());
		userDto.setEmail(userDto.getEmail());
		userDto.setPassword(userDto.getPassword());
		userDto.setAbout(userDto.getAbout());
		UserDto deactive = this.userService.deactiveUser(id, userDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user with user id "+id+" deleted successfully.",true), HttpStatus.OK);
	}
//	to reactivate user
	@PutMapping(value = "/active/{id}",produces = "application/json")
	public ResponseEntity<UserDto> setUserActive(@PathVariable Integer id,UserDto userDto) {
		userDto.setName(userDto.getName());
		userDto.setEmail(userDto.getEmail());
		userDto.setPassword(userDto.getPassword());
		userDto.setAbout(userDto.getAbout());
		UserDto active = this.userService.setUserActive(id, userDto);
		return new ResponseEntity<UserDto>(active, HttpStatus.OK);
	}
	
//	to delete the user
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user with user id "+id+" deleted successfully.",true), HttpStatus.OK);
	}

}
