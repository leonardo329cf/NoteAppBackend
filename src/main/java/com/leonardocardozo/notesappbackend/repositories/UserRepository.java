package com.leonardocardozo.notesappbackend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardocardozo.notesappbackend.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

	public User findByUsername(String username);
	
	 @Query(value = "SELECT * FROM TB_USER WHERE USERNAME LIKE %?1% AND LOWER(NAME) LIKE %?2%", nativeQuery = true)
	public List<User> findByUsernameAndNameContaining(String username, String name);

}
