package com.leonardocardozo.notesappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.leonardocardozo.notesappbackend.repositories.UserRepository;

@SpringBootApplication
public class NoteAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteAppBackendApplication.class, args);
	}

}
