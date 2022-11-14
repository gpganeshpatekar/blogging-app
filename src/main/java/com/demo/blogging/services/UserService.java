package com.demo.blogging.services;

import java.util.List;

import com.demo.blogging.payloads.UserDto;

public interface UserService {
//	to create user
	UserDto createUser(UserDto userDto);
//	to get all list of users
	List<UserDto> getListOfUsers();
//	to get all list of active users
	List<UserDto> getListOfActiveUsers();
//	to get a user by user id
	UserDto getUser(Integer id);
//	to update user
	UserDto updateUser(Integer id,UserDto userDto);
//	to deactivate user (soft delete)
	UserDto deactiveUser(Integer id,UserDto userDto);
//	to reactivate user
	UserDto setUserActive(Integer id, UserDto userDto);
//	to delete the user
	void deleteUser(Integer id);

	
	
	
	
	

}
