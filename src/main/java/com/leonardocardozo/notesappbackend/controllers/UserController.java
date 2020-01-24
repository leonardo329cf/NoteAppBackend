package com.leonardocardozo.notesappbackend.controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

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

import com.leonardocardozo.notesappbackend.entities.User;
import com.leonardocardozo.notesappbackend.entities.utils.UserUtil;
import com.leonardocardozo.notesappbackend.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * @GetMapping public ResponseEntity<List<UserUtil>> findAll() { var list =
	 * userService.findAll(); return ResponseEntity.ok().body(list); }
	 */

	@GetMapping
	public ResponseEntity<List<UserUtil>> findByUsernameAndNameContaining(
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		var list = userService.findByUsernameAndNameContaining(username, name);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<UserUtil> findByUsername(@PathVariable String username) {
		var user = userService.findByUsername(username);
		var resp = new UserUtil(user.getUsername(), user.getName(), user.getPicUrl());
		return ResponseEntity.ok().body(resp);
	}

	@PostMapping
	public ResponseEntity<UserUtil> insert(@RequestBody User user) {
		var resp = userService.insert(user);
		var uri = fromCurrentRequest().path("/{username}").buildAndExpand(user.getUsername()).toUri();
		return ResponseEntity.created(uri).body(resp);
	}

	@PutMapping(value = "/{username}")
	public ResponseEntity<UserUtil> update(@PathVariable String username, @RequestBody User user) {
		var resp = userService.update(username, user);
		return ResponseEntity.ok().body(resp);
	}

	@DeleteMapping(value = "/{username}")
	public ResponseEntity<String> delete(@PathVariable String username) {
		var resp = userService.delete(username);
		return ResponseEntity.ok().body(resp);
	}
}
