package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.entities.utils.UserUtil;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserUtil> findAll(){
		List<UserUtil> list = new ArrayList<>();
		for(User user : userRepository.findAll()) {
			list.add(userToUserUtil(user));
		}
		return list;
	}
	
	public UserUtil findByUsername(String username) {
		var user = userRepository.findByUsername(username);
		var resp = userToUserUtil(user);
		return resp;
	}
	
	public List<UserUtil> findByUsernameAndNameContaining(String username, String name) {
		username = username.toLowerCase();
		name = name.toLowerCase();
		var baseList = userRepository.findByUsernameAndNameContaining(username, name);
		List<UserUtil> resp = new ArrayList<>();
		for(User user : baseList) {
			resp.add(userToUserUtil(user));
		}
		return resp;
	}

	public UserUtil insert(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		var resp = userToUserUtil(user);
		user = userRepository.save(user);
		return resp;
	}

	public UserUtil update(String username, User user) {
		var baseUser = userRepository.findByUsername(username);
		
		user.setUsername(baseUser.getUsername());
		
		if(user.getPassword() == null) {
			user.setPassword(baseUser.getPassword());
		}
		if(user.getName() == null) {
			user.setName(baseUser.getName());
		}
		if(user.getPicUrl() == null) {
			user.setPicUrl(baseUser.getPicUrl());
		}
		
		baseUser = userRepository.save(user);
		
		var resp = userToUserUtil(user);
		return resp;
	}
	
	public String delete(String username) {
		userRepository.deleteById(username);
		String resp = "User with the following username was deleted: " + username;
		return resp;
	}
	
	private UserUtil userToUserUtil(User user) {
		return new UserUtil(user.getUsername(), user.getName(), user.getPicUrl());
	}

	

}
