package com.leonardocardozo.notesappbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardocardozo.notesappbackend.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

	@Query(value = "SELECT * FROM tb_note WHERE id = ?1", nativeQuery = true)
	public Note findNoteById(Long id);

	@Query(value = "SELECT * FROM tb_note WHERE author_username = ?1", nativeQuery = true)
	public List<Note> findByAuthor(String username);

	@Query(value = "SELECT * FROM tb_note WHERE author_username = ?1 AND LOWER(title) LIKE %?2%", nativeQuery = true)
	public List<Note> findByAuthorAndTitle(String username, String title);
}
