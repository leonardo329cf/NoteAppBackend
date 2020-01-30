package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.enums.GeneralPermission;
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
		List<NoteUtil> noteUtilList = new ArrayList<>();
		for (Note note : notes) {
			NoteUtil noteUtil = new NoteUtil().noteToNoteUtil(note);
			noteUtilList.add(noteUtil);
		}
		return noteUtilList;
	}

	public NoteUtil findById(Long id) {
		var note = noteRepo.findNoteById(id);
		if (note != null) {
			NoteUtil noteUtil = new NoteUtil().noteToNoteUtil(note);
			return noteUtil;
		} else {
			throw new ResourceNotFoundException(id.toString());
		}
	}
	
	public List<NoteUtil> findByAuthorAndTitle(String username, String title) {
		title = title.toLowerCase();
		List<Note> notes = noteRepo.findByAuthorAndTitle(username, title);
		List<NoteUtil> noteUtilList = new ArrayList<>();
		for (Note note : notes) {
			NoteUtil noteUtil = new NoteUtil().noteToNoteUtil(note);
			noteUtilList.add(noteUtil);;
		}
		return noteUtilList;
	}
	
	public NoteUtil insert(String username, Note note) {
		var author = userRepo.findByUsername(username);
		if(author != null) {
			note.setAuthor(author);
			noteRepo.save(note);
			NoteUtil noteUtil = new NoteUtil().noteToNoteUtil(note);
			return noteUtil;
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
				note.setGeneralPermission(GeneralPermission.valueOf(baseNote.getGeneralPermission()));
			}
			if(note.getAuthor() == null) {
				note.setAuthor(baseNote.getAuthor());
			}
			if(note.getContributors() == null) {
				note.getContributors().addAll(baseNote.getContributors());
			}
			noteRepo.save(note);
			NoteUtil noteUtil = new NoteUtil().noteToNoteUtil(note);
			return noteUtil;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}
	
	public NoteUtil delete(Long id) {
		try {
			Note note = noteRepo.findNoteById(id);
			NoteUtil noteUtil = new NoteUtil().noteToNoteUtil(note);
			noteRepo.deleteById(id);
			return noteUtil;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(id.toString());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}
}
