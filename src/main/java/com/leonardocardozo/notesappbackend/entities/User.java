package com.leonardocardozo.notesappbackend.entities;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbUser")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	
	private String password;
	
	private boolean active;
	
	private String roles;
	
	private String name;
	private String picUrl;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	private Set<Note> myNotes = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.contributor", cascade = CascadeType.REMOVE)
	private Set<Contribution> contributions = new HashSet<>();
	
	
	public User() {
		
	}
	public User(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.picUrl = "";
		this.setRoles("USER");
		this.setActive(true);
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Set<Note> getNotes() {
		Set<Note> set = new HashSet<>();
		for(Note n : myNotes) {
			set.add(n);
		}
		return set;
	}

	public Set<Note> getContributions(){
		Set<Note> set = new HashSet<>();
		for(Contribution x : contributions) {
			set.add((x.getNote()));
		}
		return set;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
