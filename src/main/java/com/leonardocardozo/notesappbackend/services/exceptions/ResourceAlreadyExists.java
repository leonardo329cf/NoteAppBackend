package com.leonardocardozo.notesappbackend.services.exceptions;

public class ResourceAlreadyExists extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExists(String id) {
		super("Id, " + id + ", conflicts with existing one");
	}
}
