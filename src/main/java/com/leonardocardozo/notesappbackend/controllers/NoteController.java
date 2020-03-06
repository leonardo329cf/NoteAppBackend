package com.leonardocardozo.notesappbackend.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.leonardocardozo.notesappbackend.security.exceptions.ForbiddenException;
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

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<List<NoteUtil>> findAll() {
		var resp = noteService.findAll();
		return ResponseEntity.ok().body(resp);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<NoteUtil> findById(@PathVariable Long id, Principal principal) {
		NoteUtil resp = noteService.findById(id);
		if (
				resp.getAuthor().equals(principal.getName())
				|| contService.isContributor(principal.getName(), id, 0)
				|| resp.getGeneralPermission() == 1
				|| resp.getGeneralPermission() == 2
				) {
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<NoteUtil> update(@PathVariable Long id, @RequestBody Note note, Principal principal) {
		NoteUtil baseNote = noteService.findById(id);
		if (
				baseNote.getAuthor().equals(principal.getName())
				|| contService.isContributor(principal.getName(), id, 1)
				|| baseNote.getGeneralPermission() == 2
				) {
			var resp = noteService.update(id, note);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<NoteUtil> delete(@PathVariable Long id, Principal principal) {
		if (noteService.findById(id).getAuthor().equals(principal.getName())) {
			var resp = noteService.delete(id);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}

	// Contributor related requests

	@GetMapping(value = "/{id}/contributions")
	public ResponseEntity<List<ContributionUtil>> findContributions(@PathVariable("id") Long id, Principal principal) {
		if (noteService.findById(id).getAuthor().equals(principal.getName())) {
			List<ContributionUtil> resp = contService.findByNoteId(id);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}

	@PostMapping(value = "/{id}/contributions")
	public ResponseEntity<ContributionUtil> insertContributor(@PathVariable Long id,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "generalPermission", required = true) Integer contributionPermission,
			Principal principal) {
		if (noteService.findById(id).getAuthor().equals(principal.getName())) {
			var resp = contService.insert(username, id, contributionPermission);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}

	@PutMapping(value = "/{id}/contributions")
	public ResponseEntity<ContributionUtil> updateContributor(@PathVariable Long id,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "generalPermission", required = true) Integer contributionPermission,
			Principal principal) {
		if (noteService.findById(id).getAuthor().equals(principal.getName())) {
			var resp = contService.update(username, id, contributionPermission);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}

	@DeleteMapping(value = "/{id}/contributions")
	public ResponseEntity<ContributionUtil> deleteContributor(@PathVariable Long id,
			@RequestParam(name = "username", required = true) String username, Principal principal) {
		if (noteService.findById(id).getAuthor().equals(principal.getName())) {
			var resp = contService.delete(username, id);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}
}
