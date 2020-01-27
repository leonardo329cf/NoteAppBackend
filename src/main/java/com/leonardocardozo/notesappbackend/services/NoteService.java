package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.utils.NoteUtil;
import com.leonardocardozo.notesappbackend.repositories.NoteRepository;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceNotFoundException;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepo;

	public List<NoteUtil> findAll() {
		var notes = noteRepo.findAll();
		List<NoteUtil> resp = new ArrayList<>();
		for (Note n : notes) {
			resp.add(noteToNoteUtil(n));
		}
		return resp;
	}

	public NoteUtil findById(Long id) {
		var note = noteRepo.findNoteById(id);
		if (note != null) {
			NoteUtil resp = noteToNoteUtil(note);
			return resp;
		} else {
			throw new ResourceNotFoundException(id.toString());
		}
	}

	public List<NoteUtil> findByAuthor(String username) {
		List<Note> note = noteRepo.findByAuthor(username);
		List<NoteUtil> resp = new ArrayList<>();
		for (Note n : note) {
			resp.add(noteToNoteUtil(n));
		}
		return resp;
	}
	
	//util function
	private NoteUtil noteToNoteUtil(Note note) {
		var userNote = new NoteUtil(note.getId(), note.getTitle(), note.getContent(), note.getGeneralPermission(),
				note.getAuthor().getUsername());
		return userNote;
	}
}
