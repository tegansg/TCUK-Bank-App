package io.zipcoder.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;
//14/09/2017 Currently not in use
//implemented if had time

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException() {
		super();
		
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ResourceNotFoundException(String message) {
		super(message);
		
	}

}
