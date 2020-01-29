package com.leonardocardozo.notesappbackend.services.exceptions;

public class ResourceDoesNotPermitException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceDoesNotPermitException(String msg) {
		super(msg);
	}

}
