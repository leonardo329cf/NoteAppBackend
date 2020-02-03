package com.leonardocardozo.notesappbackend.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.utils.ContributionUtil;
import com.leonardocardozo.notesappbackend.entities.utils.NoteUtil;
import com.leonardocardozo.notesappbackend.services.ContributionService;
import com.leonardocardozo.notesappbackend.services.NoteService;

@RestController
@RequestMapping(value = "/notes")
public class NoteController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private ContributionService contService;

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
	public ResponseEntity<NoteUtil> delete(@PathVariable Long id) {
		var resp = noteService.delete(id);
		return ResponseEntity.ok().body(resp);
	}
	
	//Contributor related requests
	
	@GetMapping(value = "/{id}/contributions")
	public ResponseEntity<List<ContributionUtil>> findContributions(@PathVariable("id") Long id) {
		List<ContributionUtil> resp = contService.findByNoteId(id);
		return ResponseEntity.ok().body(resp);
	}
	
	@PostMapping(value = "/{id}/contributions")
	public ResponseEntity<ContributionUtil> insertContributor(
			@PathVariable Long id,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "generalPermission", required = true) Integer contributionPermission
			){
		var resp = contService.insert(username, id, contributionPermission);
		return ResponseEntity.ok().body(resp);
	}
	
	@PutMapping(value = "/{id}/contributions")
	public ResponseEntity<ContributionUtil> updateContributor(
			@PathVariable Long id,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "generalPermission", required = true) Integer contributionPermission
			){
		var resp = contService.update(username, id, contributionPermission);
		return ResponseEntity.ok().body(resp);
	}
	
	@DeleteMapping(value = "/{id}/contributions")
	public ResponseEntity<ContributionUtil> deleteContributor(
			@PathVariable Long id,
			@RequestParam(name = "username", required = true) String username
			){
		var resp = contService.delete(username, id);
		return ResponseEntity.ok().body(resp);
	}
}
