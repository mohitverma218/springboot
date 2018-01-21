package com.sms.exception;

public class SuperheroAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -2376038307583996587L;

    public SuperheroAlreadyExistException(String message) {
        super(message);
    }
}