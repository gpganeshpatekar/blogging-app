package com.demo.blogging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.entities.Role;
import com.demo.blogging.entities.User;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.payloads.UserDto;
import com.demo.blogging.repositories.RoleRepository;
import com.demo.blogging.repositories.UserRepository;
import com.demo.blogging.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	to create user
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User save = this.userRepository.save(user);
		return this.modelMapper.map(save, UserDto.class);
	}

//	to get all list of users
	@Override
	public List<UserDto> getListOfUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(u -> this.modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

//	to get all list of active users
	@Override
	public List<UserDto> getListOfActiveUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().filter(u -> u.isActive()).map(u -> this.modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

//	to get a user by user id
	@Override
	public UserDto getUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		return this.modelMapper.map(user, UserDto.class);
	}
	
//	to update user
	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser = this.userRepository.save(user);
		return this.modelMapper.map(updateUser, UserDto.class);
	}
	
//	to deactivate user (soft delete)
	@Override
	public UserDto deactiveUser(Integer id, UserDto userDto) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		user.setActive(false);
		User softDelete = this.userRepository.save(user);
		return this.modelMapper.map(softDelete, UserDto.class);
	}
	
//	to reactivate user
	@Override
	public UserDto setUserActive(Integer id, UserDto userDto) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		user.setActive(true);
		User softDelete = this.userRepository.save(user);
		return this.modelMapper.map(softDelete, UserDto.class);
	}
	
//	to delete the user	
	@Override
	public void deleteUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		this.userRepository.delete(user);
	}
//  to register new user
	@Override
	public UserDto registerNewUser(UserDto userDto) {
	
		User user = this.modelMapper.map(userDto, User.class);
		// user generated password will be encoded using password encoder
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepository.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
