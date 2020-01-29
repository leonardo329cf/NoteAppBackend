package com.leonardocardozo.notesappbackend.entities.utils;

import java.io.Serializable;

import com.leonardocardozo.notesappbackend.entities.Contributor;

public class ContributorUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contributorUsername;
	private Long noteId;
	private String noteTitle;
	private Integer contributorPermission;
	
	
	public ContributorUtil() {
		
	};
	public ContributorUtil(String contributorUsername, Long noteId, String noteTitle, Integer contributorPermission) {
		super();
		this.contributorUsername = contributorUsername;
		this.noteId = noteId;
		this.noteTitle = noteTitle;
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
	
	public ContributorUtil contToContUtil(Contributor cont) {
		ContributorUtil con = new ContributorUtil(
				cont.getContributor().getName(),
				cont.getNote().getId(),
				cont.getNote().getTitle(),
				cont.getPermission());
		return con;
	}
}
