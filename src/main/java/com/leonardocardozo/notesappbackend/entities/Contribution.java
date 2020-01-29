package com.leonardocardozo.notesappbackend.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leonardocardozo.notesappbackend.entities.enums.ContributorPermission;
import com.leonardocardozo.notesappbackend.entities.pk.ContributorPK;

@Entity
@Table(name = "tbContribution")
public class Contribution implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ContributorPK id = new ContributorPK();
	
	private Integer contributorPermission;
	
	
	public Contribution() {
	}
	public Contribution(User contributor, Note note, ContributorPermission contributorPermission) {
		super();
		id.setContributor(contributor);
		id.setNotes(note);
		this.contributorPermission = contributorPermission.getCode();
	}
	
	
	public User getContributor() {
		return id.getContributors();
	}
	public void setContributor(User contributor) {
		id.setContributor(contributor);
	}
	public Note getNote() {
		return id.getNotes();
	}
	public void setNote(Note note) {
		id.setNotes(note);
	}
	public Integer getPermission() {
		return contributorPermission;
	}
	public void setPermission(ContributorPermission contributorPermission) {
		this.contributorPermission = contributorPermission.getCode();
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
		Contribution other = (Contribution) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
