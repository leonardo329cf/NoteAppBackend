package com.leonardocardozo.notesappbackend.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String id) {
		super("Id, " + id + ", not found");
	}
}
