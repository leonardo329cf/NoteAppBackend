package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.entities.utils.UserUtil;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceAlreadyExists;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserUtil> findAll() {
		List<UserUtil> userUtilList = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			UserUtil userUtil = new UserUtil().userToUserUtil(user);
			userUtilList.add(userUtil);
		}
		return userUtilList;
	}

	public UserUtil findByUsername(String username) {
		try {
			var user = userRepository.findByUsername(username);
			UserUtil userUtil = new UserUtil().userToUserUtil(user);
			return userUtil;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(username);
		}
	}

	public List<UserUtil> findByUsernameAndNameContaining(String username, String name) {
		username = username.toLowerCase();
		name = name.toLowerCase();
		var userList = userRepository.findByUsernameAndNameContaining(username, name);
		List<UserUtil> userUtilList = new ArrayList<>();
		for (User user : userList) {
			UserUtil userUtil = new UserUtil().userToUserUtil(user);
			userUtilList.add(userUtil);
		}
		return userUtilList;
	}

	public UserUtil insert(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		if (userRepository.findByUsername(user.getUsername()) == null) {
			user = userRepository.save(user);
			UserUtil userUtil = new UserUtil().userToUserUtil(user);
			return userUtil;
		} else {
			throw new ResourceAlreadyExists(user.getUsername());
		}
	}

	public UserUtil update(String username, User user) {
		try {
			var baseUser = userRepository.findByUsername(username);
			user.setUsername(baseUser.getUsername());

			if (user.getPassword() == null) {
				user.setPassword(baseUser.getPassword());
			}
			if (user.getName() == null) {
				user.setName(baseUser.getName());
			}
			if (user.getPicUrl() == null) {
				user.setPicUrl(baseUser.getPicUrl());
			}
			if (user.getNotes() == null) {
				user.getNotes().addAll(baseUser.getNotes());
			}
			if (user.getContributions() == null) {
				user.getContributions().addAll(baseUser.getContributions());
			}
			baseUser = userRepository.save(user);
			UserUtil userUtil = new UserUtil().userToUserUtil(baseUser);
			return userUtil;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(username);
		}
	}

	public String delete(String username) {
		try {
			userRepository.deleteById(username);
			String resp = username + " deleted";
			return resp;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(username);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(username);
		}
	}

	//util conversion
	

}
