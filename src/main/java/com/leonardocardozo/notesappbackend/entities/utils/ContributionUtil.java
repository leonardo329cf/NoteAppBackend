package com.leonardocardozo.notesappbackend.entities.utils;

import java.io.Serializable;

import com.leonardocardozo.notesappbackend.entities.Contribution;

public class ContributionUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contributorUsername;
	private Long noteId;
	private String noteAuthor;
	private String noteTitle;
	private Integer contributorPermission;
	
	
	public ContributionUtil() {
		
	};
	public ContributionUtil(String contributorUsername, Long noteId, String noteTitle, String noteAuthor, Integer contributorPermission) {
		super();
		this.contributorUsername = contributorUsername;
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteAuthor = noteAuthor;
		this.contributorPermission = contributorPermission;
	}
	
	
	public String getContributorUsername() {
		return contributorUsername;
	}
	public void setContributorUsername(String contributorUsername) {
		this.contributorUsername = contributorUsername;
	}
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public String getNoteAuthor() {
		return noteAuthor;
	}
	public void setNoteAuthor(String noteAuthor) {
		this.noteAuthor = noteAuthor;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public Integer getContributorPermission() {
		return contributorPermission;
	}
	public void setContributorPermission(Integer contributorPermission) {
		this.contributorPermission = contributorPermission;
	}
	
	public ContributionUtil contToContUtil(Contribution cont) {
		ContributionUtil con = new ContributionUtil(
				cont.getContributor().getName(),
				cont.getNote().getId(),
				cont.getNote().getTitle(),
				cont.getNote().getAuthor().getUsername(),
				cont.getPermission());
		return con;
	}
}
