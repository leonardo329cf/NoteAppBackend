package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.Contributor;
import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.utils.ContributorUtil;
import com.leonardocardozo.notesappbackend.entities.utils.NoteUtil;
import com.leonardocardozo.notesappbackend.repositories.NoteRepository;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceNotFoundException;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepo;
	
	@Autowired
	private UserRepository userRepo;
	

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
	
	public List<NoteUtil> findByAuthorAndTitle(String username, String title) {
		title = title.toLowerCase();
		List<Note> note = noteRepo.findByAuthorAndTitle(username, title);
		List<NoteUtil> resp = new ArrayList<>();
		for (Note n : note) {
			resp.add(noteToNoteUtil(n));
		}
		return resp;
	}
	
	public NoteUtil insert(String username, Note note) {
		var author = userRepo.findByUsername(username);
		if(author != null) {
			note.setAuthor(author);
			noteRepo.save(note);
			var resp = noteToNoteUtil(note);
			return resp;
		} else {
			throw new ResourceNotFoundException(username);
		}
	}
	
	public NoteUtil update(Long id, Note note) {
		try {
			Note baseNote = noteRepo.findNoteById(id);
			note.setId(baseNote.getId());
			if(note.getTitle() == null) {
				note.setTitle(baseNote.getTitle());
			}
			if(note.getContent() == null) {
				note.setContent(baseNote.getContent());
			}
			if(note.getGeneralPermission() == null) {
				note.setGeneralPermission(baseNote.getGeneralPermission());
			}
			if(note.getAuthor() == null) {
				note.setAuthor(baseNote.getAuthor());
			}
			if(note.getContributors() == null) {
				note.getContributors().addAll(baseNote.getContributors());
			}
			noteRepo.save(note);
			var resp = noteToNoteUtil(note);
			return resp;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}
	
	public String delete(Long id) {
		try {
			noteRepo.deleteById(id);
			String resp = id.toString() + " deleted";
			return resp;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(id.toString());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}
	
	//util function
	private NoteUtil noteToNoteUtil(Note note) {
		NoteUtil userNote = new NoteUtil(note.getId(),
				note.getTitle(),
				note.getContent(),
				note.getGeneralPermission(),
				note.getAuthor().getUsername());
		for(Contributor c : note.getContributors()) {
			ContributorUtil con = new ContributorUtil(
					c.getContributor().getName(),
					c.getNote().getId(),
					c.getPermission());
			userNote.getContributors().add(con);
		}
		return userNote;
	}
}
