package com.datta.authentication_service.exceptions;

public class IncorrectCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectCredentialsException(String message) {
		super(message);
	}
}