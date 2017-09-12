package io.zipcoder.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateFormatException extends IOException {
	
	public InvalidDateFormatException() {
		super();
		
	}

	public InvalidDateFormatException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public InvalidDateFormatException(String message) {
		super(message);
		
	}
	
	
}
