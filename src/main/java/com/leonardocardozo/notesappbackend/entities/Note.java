package com.leonardocardozo.notesappbackend.entities;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leonardocardozo.notesappbackend.entities.enums.GeneralPermission;

@Entity
@Table(name = "tbNote")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private String title;
	private String content;
	
	private Integer generalPermission;
	
	@JsonIgnore
	@ManyToOne(fetch = LAZY)
	private User author;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.note")
	private Set<Contributor> contributors = new HashSet<>();

	
	public Note() {
		
	}
	public Note(String title, String content, GeneralPermission generalPermission, User author) {
		super();
		this.title = title;
		this.content = content;
		this.generalPermission = generalPermission.getCode();
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
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Set<Contributor> getContributors() {
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
		Note other = (Note) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
