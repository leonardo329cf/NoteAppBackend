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
		List<UserUtil> list = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			list.add(userToUserUtil(user));
		}
		return list;
	}

	public UserUtil findByUsername(String username) {
		try {
			var user = userRepository.findByUsername(username);
			var resp = userToUserUtil(user);
			return resp;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(username);
		}
	}

	public List<UserUtil> findByUsernameAndNameContaining(String username, String name) {
		username = username.toLowerCase();
		name = name.toLowerCase();
		var baseList = userRepository.findByUsernameAndNameContaining(username, name);
		List<UserUtil> resp = new ArrayList<>();
		for (User user : baseList) {
			resp.add(userToUserUtil(user));
		}
		return resp;
	}

	public UserUtil insert(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		if (userRepository.findByUsername(user.getUsername()) == null) {
			userRepository.save(user);
			return userToUserUtil(user);
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
			var resp = userToUserUtil(user);
			baseUser = userRepository.save(user);
			return resp;
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

	private UserUtil userToUserUtil(User user) {
		return new UserUtil(user.getUsername(), user.getName(), user.getPicUrl());
	}

}
