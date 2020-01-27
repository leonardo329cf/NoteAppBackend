package com.leonardocardozo.notesappbackend.entities.utils;

import java.io.Serializable;
import java.util.Set;

public class NoteUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String content;
	private Integer generalPermission;
	private String author;
	private Set<String> contributors;
	
	
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
	public Set<String> getContributors() {
		return contributors;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteUtil other = (NoteUtil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
