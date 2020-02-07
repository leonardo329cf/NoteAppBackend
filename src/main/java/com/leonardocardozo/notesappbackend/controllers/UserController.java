package com.leonardocardozo.notesappbackend.controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.leonardocardozo.notesappbackend.entities.Note;
import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.entities.utils.ContributionUtil;
import com.leonardocardozo.notesappbackend.entities.utils.NoteUtil;
import com.leonardocardozo.notesappbackend.entities.utils.UserUtil;
import com.leonardocardozo.notesappbackend.security.exceptions.ForbiddenException;
import com.leonardocardozo.notesappbackend.services.ContributionService;
import com.leonardocardozo.notesappbackend.services.NoteService;
import com.leonardocardozo.notesappbackend.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private ContributionService contService;

	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserUtil>> findByUsernameAndNameContaining(
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "name", required = false, defaultValue = "") String name
			) {
		var resp = userService.findByUsernameAndNameContaining(username, name);
		return ResponseEntity.ok().body(resp);
	}
	
	
	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserUtil> findByUsername(@PathVariable String username) {
		var user = userService.findByUsername(username);
		var resp = new UserUtil(user.getUsername(), user.getName(), user.getPicUrl());
		return ResponseEntity.ok().body(resp);
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public ResponseEntity<UserUtil> insert(@RequestBody User user) {
		var resp = userService.insert(user);
		var uri = fromCurrentRequest().path("/{username}").buildAndExpand(user.getUsername()).toUri();
		return ResponseEntity.created(uri).body(resp);
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
	public ResponseEntity<UserUtil> update(
			@PathVariable String username,
			@RequestBody User user,
			Principal principal
			) {
		if(principal.getName().equals(username)) {
			var resp = userService.update(username, user);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden: " + username + " != " + principal.getName());
		}

	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<UserUtil> delete(
			@PathVariable String username,
			Principal principal
			) {
		if(principal.getName().equals(username)) {
			var resp = userService.delete(username);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}
	
	//Note related requests
	
	@RequestMapping(value = "/users/{username}/notes", method = RequestMethod.GET)
	public ResponseEntity<List<NoteUtil>> findByAuthorAndTitle(
			@PathVariable String username,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			Principal principal
			) {
		if(principal.getName().equals(username)) {
			userService.findByUsername(username);
			var resp = noteService.findByAuthorAndTitle(username, title);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}

	}
	
	@RequestMapping(value = "/users/{username}/notes", method = RequestMethod.POST)
	public ResponseEntity<NoteUtil> insertNote(
			@PathVariable String username,
			@RequestBody Note note,
			Principal principal
			) {
 		if(principal.getName().equals(username)) {
 			NoteUtil resp = noteService.insert(username, note);
 			String toBeReplaced = "/users/" + username + "/notes";
 			String replacement = "/notes/" + resp.getId().toString();
 			String baseUri = fromCurrentRequest().path("").buildAndExpand().toUriString();
 			baseUri = baseUri.replace(toBeReplaced, replacement);
 			
 			URI uri = UriComponentsBuilder.newInstance().path(baseUri).buildAndExpand().toUri();
 			return ResponseEntity.created(uri).body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}
	
	//Contribution related requests
	
	@RequestMapping(value = "/users/{username}/contributions", method = RequestMethod.GET)
	public ResponseEntity<List<ContributionUtil>> findContributions(
			@PathVariable String username,
			Principal principal
			){
		if(principal.getName().equals(username)) {
			List<ContributionUtil> resp = contService.findByUsername(username);
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}
}
