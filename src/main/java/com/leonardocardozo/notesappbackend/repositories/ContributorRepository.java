package com.leonardocardozo.notesappbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardocardozo.notesappbackend.entities.Contribution;


public interface ContributorRepository extends JpaRepository<Contribution, Long> {

}
