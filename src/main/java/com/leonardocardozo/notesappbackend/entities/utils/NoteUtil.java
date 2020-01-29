package com.leonardocardozo.notesappbackend.entities.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.leonardocardozo.notesappbackend.entities.Contributor;
import com.leonardocardozo.notesappbackend.entities.Note;


public class NoteUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String content;
	private Integer generalPermission;
	private String author;
	private List<ContributorUtil> contributors = new ArrayList<>();
	
	
	public NoteUtil() {
		
	}
	public NoteUtil(Long id, String title, String content, Integer generalPermission, String author) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.generalPermission = generalPermission;
		this.author = author;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getGeneralPermission() {
		return generalPermission;
	}
	public void setGeneralPermission(Integer generalPermission) {
		this.generalPermission = generalPermission;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<ContributorUtil> getContributors() {
		return contributors;
	}
	
	
	public NoteUtil noteToNoteUtil(Note note) {
		NoteUtil userNote = new NoteUtil(note.getId(),
				note.getTitle(),
				note.getContent(),
				note.getGeneralPermission(),
				note.getAuthor().getUsername());
		for(Contributor cont : note.getContributors()) {
			ContributorUtil contUtil = new ContributorUtil().contToContUtil(cont);
			userNote.getContributors().add(contUtil);
		}
		return userNote;
	}
}
