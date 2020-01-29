package com.leonardocardozo.notesappbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardocardozo.notesappbackend.entities.Contribution;
import com.leonardocardozo.notesappbackend.entities.enums.ContributorPermission;
import com.leonardocardozo.notesappbackend.entities.utils.ContributionUtil;
import com.leonardocardozo.notesappbackend.repositories.ContributorRepository;
import com.leonardocardozo.notesappbackend.repositories.NoteRepository;
import com.leonardocardozo.notesappbackend.repositories.UserRepository;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceAlreadyExists;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceDoesNotPermitException;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceNotFoundException;

@Service
public class ContributionService {

	@Autowired
	private ContributorRepository contRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NoteRepository noteRepo;

	public List<ContributionUtil> findAll() {
		List<ContributionUtil> contUtilList = new ArrayList<>();
		var contList = contRepo.findAll();
		for (Contribution cont : contList) {
			ContributionUtil contUtil = new ContributionUtil().contToContUtil(cont);
			contUtilList.add(contUtil);
		}
		return contUtilList;
	}

	public List<ContributionUtil> findByUsername(String username) {
		if (userRepo.findByUsername(username) != null) {
			List<Contribution> contlist = contRepo.findByUsername(username);
			List<ContributionUtil> contUtilList = new ArrayList<>();
			for (Contribution cont : contlist) {
				ContributionUtil contUtil = new ContributionUtil().contToContUtil(cont);
				contUtilList.add(contUtil);
			}
			return contUtilList;
		} else {
			throw new ResourceNotFoundException(username);
		}
	}

	public List<ContributionUtil> findByNoteId(Long noteId) {
		if (noteRepo.findNoteById(noteId) != null) {
			List<Contribution> contlist = contRepo.findByNoteId(noteId);
			List<ContributionUtil> contUtilList = new ArrayList<>();
			for (Contribution cont : contlist) {
				ContributionUtil contUtil = new ContributionUtil().contToContUtil(cont);
				contUtilList.add(contUtil);
			}
			return contUtilList;
		} else {
			throw new ResourceNotFoundException(noteId.toString());
		}
	}

	public ContributionUtil insert(String username, Long noteId, Integer contributionPermission) {
		if (noteRepo.findById(noteId).orElseThrow(() -> new ResourceNotFoundException(noteId.toString()))
				.getContributors().size() >= 3) {
			throw new ResourceDoesNotPermitException("Number of contributors superior to 3");
		} else if (!(contRepo.findByUsernameAndNoteId(username, noteId).isEmpty())) {
			throw new ResourceAlreadyExists(noteId.toString() + " and username, " + username);
		} else {
			Contribution contToBeSaved = new Contribution();
			contToBeSaved.setNote(noteRepo.findNoteById(noteId));
			contToBeSaved.setContributor(userRepo.findByUsername(username));
			if (contributionPermission < 0 || contributionPermission > 1) {
				contToBeSaved.setPermission(ContributorPermission.CONTRIBUTOR_READ);
			} else {
				contToBeSaved.setPermission(ContributorPermission.valueOf(contributionPermission));
			}
			contRepo.save(contToBeSaved);
			ContributionUtil contUtil = new ContributionUtil().contToContUtil(contToBeSaved);
			return contUtil;
		}
	}

	public ContributionUtil update(String username, Long noteId, Integer contributionPermission) {
		if (!(contRepo.findByUsernameAndNoteId(username, noteId).isEmpty())) {
			Contribution contToBeSaved = new Contribution();
			contToBeSaved.setNote(noteRepo.findNoteById(noteId));
			contToBeSaved.setContributor(userRepo.findByUsername(username));
			if (contributionPermission <= 0 || contributionPermission >= 1) {
				contToBeSaved.setPermission(ContributorPermission.CONTRIBUTOR_READ);
			} else {
				contToBeSaved.setPermission(ContributorPermission.valueOf(contributionPermission));
			}
			contRepo.save(contToBeSaved);
			ContributionUtil contUtil = new ContributionUtil().contToContUtil(contToBeSaved);
			return contUtil;
			
		} else {
			throw new ResourceNotFoundException(noteId.toString() + " and username, " + username);
		}
	}
}
