package com.leonardocardozo.notesappbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leonardocardozo.notesappbackend.entities.Contributor;
import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.entities.enums.ContributorPermission;
import com.leonardocardozo.notesappbackend.entities.enums.GeneralPermission;
import com.leonardocardozo.notesappbackend.repositories.ContributorRepository;
import com.leonardocardozo.notesappbackend.repositories.NoteRepository;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NoteRepository noteRepo;
	
	@Autowired
	private ContributorRepository contributorRepo;
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("alex", "123", "Alex Schneider");
		User user2 = new User("aley", "1234", "Aley Kalashinikov");
		User user3 = new User("ana", "1235", "Ana Amelia Silva");
		User user4 = new User("analucia", "1236", "Ana Lucia Correa");
		User user5 = new User("altair", "1237", "Alatair Rocha");
		User user6 = new User("anajulia", "1238", "Ana Julia Hermanos");
		
		userRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
		
		Note note1 = new Note("Todo", "buy cheese", GeneralPermission.AUTHOR_RW, user1);
		Note note2 = new Note("Travel", "Canada, next weekend, don't forget", GeneralPermission.AUTHOR_RW, user1);
		Note note3 = new Note("Can you see this note?", "can you see?", GeneralPermission.PUBLIC_R, user2);
		Note note4 = new Note("Can you edit this note?", "can you edit?", GeneralPermission.PUBLIC_RW, user3);
		Note note5 = new Note("Let's talk here", "Hello, someone there?", GeneralPermission.PUBLIC_RW, user4);
		
		noteRepo.saveAll(Arrays.asList(note1, note2, note3, note4, note5));

		Contributor con1 = new Contributor(user2, note2, ContributorPermission.CONTRIBUTOR_READ);
		Contributor con2 = new Contributor(user1, note3, ContributorPermission.CONTRIBUTOR_WRITE);
		Contributor con3 = new Contributor(user6, note1, ContributorPermission.CONTRIBUTOR_READ);
		Contributor con4 = new Contributor(user5, note1, ContributorPermission.CONTRIBUTOR_WRITE);

		contributorRepo.saveAll(Arrays.asList(con1, con2, con3,con4));
	}
}
