package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.Contributor;
import com.leonardocardozo.notesappbackend.entities.utils.ContributorUtil;
import com.leonardocardozo.notesappbackend.repositories.ContributorRepository;

@Service
public class ContributorService {

	@Autowired
	private ContributorRepository contRepo;
	
	/*
	public List<ContributorUtil> findAll() {
		List<ContributorUtil> contUtilList = new ArrayList<>();
		var contList = contRepo.findAll();
		for(Contributor cont : contList) {
			ContributorUtil contUtil = new ContributorUtil().contToContUtil(cont);
			contUtilList.add(contUtil);
		}
		return contUtilList;
	}
	*/
}
