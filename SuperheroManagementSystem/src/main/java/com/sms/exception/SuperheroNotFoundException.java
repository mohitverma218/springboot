package com.sms.exception;

public class SuperheroNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2376038307583996587L;

	public SuperheroNotFoundException(String message) {
		super(message);
	}
}