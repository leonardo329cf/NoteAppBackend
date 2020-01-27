package com.leonardocardozo.notesappbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.entities.enums.GeneralPermission;
import com.leonardocardozo.notesappbackend.repositories.NoteRepository;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NoteRepository noteRepo;
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("alex", "123", "Alex Schneider");
		User user2 = new User("aley", "1234", "Aley Kalashinikov");
		User user3 = new User("ana", "1235", "Ana Amelia Silva");
		User user4 = new User("analucia", "1236", "Ana Lucia Correa");
		User user5 = new User("altair", "1237", "Alatair Rocha");
		User user6 = new User("anajulia", "1238", "Ana Julia Hermanos");
		
		
		Note note1 = new Note("Todo", "buy cheese", GeneralPermission.AUTHOR_RW, user1);
		Note note2 = new Note("Travel", "Canada, next weekend, don't forget", GeneralPermission.AUTHOR_RW, user1);
		Note note3 = new Note("Can you see this note?", "can you see?", GeneralPermission.PUBLIC_R, user2);
		Note note4 = new Note("Can you edit this note?", "can you edit?", GeneralPermission.PUBLIC_RW, user3);
		Note note5 = new Note("Let's talk here", "Hello, someone there?", GeneralPermission.PUBLIC_RW, user4);
		
		
		user1.getNotes().addAll(Arrays.asList(note1, note2));
		user2.getNotes().add(note3);
		user3.getNotes().add(note4);
		user4.getNotes().add(note5);
		
		
		userRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
		noteRepo.saveAll(Arrays.asList(note1, note2, note3, note4, note5));
	}
}
