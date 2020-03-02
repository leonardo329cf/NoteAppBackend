package com.leonardocardozo.notesappbackend.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardocardozo.notesappbackend.entities.utils.ContributionUtil;
import com.leonardocardozo.notesappbackend.security.exceptions.ForbiddenException;
import com.leonardocardozo.notesappbackend.services.ContributionService;


@RestController
@RequestMapping(value = "/contributions")
public class ContributionController {

	@Autowired
	private ContributionService contributionService;
	
	
	@GetMapping
	public ResponseEntity<List<ContributionUtil>> findAll(Principal principal) {
		if(principal.getName() == "admin") {
			var resp = contributionService.findAll();
			return ResponseEntity.ok().body(resp);
		} else {
			throw new ForbiddenException("Forbidden");
		}
	}
}
