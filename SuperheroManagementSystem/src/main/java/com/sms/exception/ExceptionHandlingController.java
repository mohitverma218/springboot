package com.sms.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(SuperheroNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(SuperheroNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setCode("404");
		response.setMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SuperheroAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> resourceAlreadyExist(SuperheroAlreadyExistException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setCode("409");
		response.setMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AllyPublisherNotMatchingException.class)
	public ResponseEntity<ExceptionResponse> allyPublisherDifferent(AllyPublisherNotMatchingException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setCode("400");
		response.setMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		ExceptionResponse response = new ExceptionResponse();
		response.setCode("400");
		response.setMessage("Invalid inputs.");
		List<String> errors = new ArrayList<String>();
		for (ObjectError objectError : result.getAllErrors()) {
			errors.add(objectError.getDefaultMessage());
		}
		response.setErrors(errors);
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
