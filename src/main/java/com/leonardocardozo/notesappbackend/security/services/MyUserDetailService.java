package com.leonardocardozo.notesappbackend.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;
import com.leonardocardozo.notesappbackend.security.details.MyUserDetails;


@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;

	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Not found: " + username);
		} else {
			var userDetails = new MyUserDetails(user);
			return userDetails;
		}
		
	}

}
