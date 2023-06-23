package com.vitor.helpdesk.services.exceptions;

public class ObjectNotFoundExceptions extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundExceptions(String message) {
		super(message);
	}
	
	
	
}