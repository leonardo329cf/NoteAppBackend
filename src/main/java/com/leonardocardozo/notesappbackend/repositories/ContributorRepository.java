package com.leonardocardozo.notesappbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.leonardocardozo.notesappbackend.entities.Contribution;


public interface ContributorRepository extends JpaRepository<Contribution, Long> {
	
	@Query(value = "SELECT * FROM TB_CONTRIBUTION WHERE CONTRIBUTOR_USERNAME = ?1",
			nativeQuery =  true)
	List<Contribution> findByUsername(String username);
	
	@Query(value = "SELECT * FROM TB_CONTRIBUTION WHERE NOTE_ID = ?1",
			nativeQuery =  true)
	List<Contribution> findByNoteId(Long noteId);
	
	@Query(value = "SELECT * FROM TB_CONTRIBUTION WHERE CONTRIBUTOR_USERNAME = ?1 AND NOTE_ID = ?2",
			nativeQuery =  true)
	Contribution findByUsernameAndNoteId(String username, Long noteId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM TB_CONTRIBUTION WHERE CONTRIBUTOR_USERNAME = ?1 AND NOTE_ID = ?2",
			nativeQuery = true)
	void deleteByUsernameAndNoteId(String username, Long noteId);
	
}
