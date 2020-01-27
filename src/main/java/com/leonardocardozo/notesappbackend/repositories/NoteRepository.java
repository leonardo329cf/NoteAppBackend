package com.leonardocardozo.notesappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardocardozo.notesappbackend.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
