package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.Contribution;
import com.leonardocardozo.notesappbackend.entities.utils.ContributionUtil;
import com.leonardocardozo.notesappbackend.repositories.ContributorRepository;

@Service
public class ContributorService {

	@Autowired
	private ContributorRepository contRepo;
	
	
	public List<ContributionUtil> findAll() {
		List<ContributionUtil> contUtilList = new ArrayList<>();
		var contList = contRepo.findAll();
		for(Contribution cont : contList) {
			ContributionUtil contUtil = new ContributionUtil().contToContUtil(cont);
			contUtilList.add(contUtil);
		}
		return contUtilList;
	}
}
