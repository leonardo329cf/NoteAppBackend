package com.leonardocardozo.notesappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardocardozo.notesappbackend.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

	@Query(value = "SELECT * FROM TB_NOTE WHERE ID = ?1", nativeQuery = true)
	public Note findNoteById(Long id);
}
