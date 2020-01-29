package com.leonardocardozo.notesappbackend.entities.utils;

import java.io.Serializable;

public class ContributorUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contributorUsername;
	private Long noteId;
	private Integer contributorPermission;
	
	
	public ContributorUtil() {
		
	};
	public ContributorUtil(String contributorUsername, Long noteId, Integer contributorPermission) {
		super();
		this.contributorUsername = contributorUsername;
		this.noteId = noteId;
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
	public Integer getContributorPermission() {
		return contributorPermission;
	}
	public void setContributorPermission(Integer contributorPermission) {
		this.contributorPermission = contributorPermission;
	}
	
	
	@Override
	public String toString() {
		return "ContributorUtil [contributorUsername=" + contributorUsername + ", noteId=" + noteId
				+ ", contributorPermission=" + contributorPermission + "]";
	}
}
