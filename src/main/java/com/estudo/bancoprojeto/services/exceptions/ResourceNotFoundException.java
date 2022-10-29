package com.estudo.bancoprojeto.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object userId) {
		super("Resource not found. userId" + userId);
	}
}
