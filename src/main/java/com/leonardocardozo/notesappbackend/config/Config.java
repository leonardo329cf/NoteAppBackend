package com.leonardocardozo.notesappbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner{

	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("alex", "123", "Alex Schneider");
		User user2 = new User("aley", "1234", "Aley Kalashinikov");
		User user3 = new User("ana", "1235", "Ana Amelia Silva");
		User user4 = new User("analucia", "1236", "Ana Lucia Correa");
		User user5 = new User("altair", "1237", "Alatair Rocha");
		User user6 = new User("anajulia", "1238", "Ana Julia Hermanos");
		
		userRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
		
	}
}
