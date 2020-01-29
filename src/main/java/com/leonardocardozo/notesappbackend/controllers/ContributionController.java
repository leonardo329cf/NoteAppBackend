package com.leonardocardozo.notesappbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardocardozo.notesappbackend.entities.utils.ContributionUtil;
import com.leonardocardozo.notesappbackend.services.ContributorService;

@RestController
@RequestMapping(value = "/contributions")
public class ContributionController {

	@Autowired
	private ContributorService contributorService;
	
	@GetMapping
	public List<ContributionUtil> findAll() {
		var contUtilList = contributorService.findAll();
		return contUtilList;
	}
}
