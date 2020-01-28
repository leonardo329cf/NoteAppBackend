package com.leonardocardozo.notesappbackend.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.utils.NoteUtil;
import com.leonardocardozo.notesappbackend.services.NoteService;

@RestController
@RequestMapping(value = "/notes")
public class NoteController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private NoteService noteService;

	@GetMapping
	public ResponseEntity<List<NoteUtil>> findAll() {
		var resp = noteService.findAll();
		return ResponseEntity.ok().body(resp);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<NoteUtil> findById(@PathVariable Long id) {
		NoteUtil resp = noteService.findById(id);
		return ResponseEntity.ok().body(resp);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<NoteUtil> update(@PathVariable Long id, @RequestBody Note note) {
		var resp = noteService.update(id, note);
		return ResponseEntity.ok().body(resp);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		var resp = noteService.delete(id);
		return ResponseEntity.ok().body(resp);
	}
}
