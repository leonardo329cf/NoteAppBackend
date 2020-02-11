package com.leonardocardozo.notesappbackend.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.leonardocardozo.notesappbackend.security.exceptions.ForbiddenException;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceAlreadyExists;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceNotFoundException;
import com.leonardocardozo.notesappbackend.services.exceptions.ResourceDoesNotPermitException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(
			ResourceNotFoundException e,
			HttpServletRequest request
			){
		String error = "Resource Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<StandardError> resourceAlreadyExists(
			ResourceAlreadyExists e,
			HttpServletRequest request
			){
		String error = "Conflict with existing resource";
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceDoesNotPermitException.class)
	public ResponseEntity<StandardError> ResourceDoesNotPermitException(
			ResourceDoesNotPermitException e,
			HttpServletRequest request
			){
		String error = "Resource does not permit";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<StandardError> ForbiddenException(
			ForbiddenException e,
			HttpServletRequest request
			){
	String error = "Forbidden";
	HttpStatus status = HttpStatus.FORBIDDEN;
	StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}
}
