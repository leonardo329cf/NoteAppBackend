package com.leonardocardozo.notesappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardocardozo.notesappbackend.entities.Contributor;


public interface ContributorRepository extends JpaRepository<Contributor, Long> {

}
