package io.zipcoder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//14/09/2017 Currently not in use
//implemented if had time

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class InsufficientFundsException extends RuntimeException {
	
	public InsufficientFundsException() {
		super();

	}

	public InsufficientFundsException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public InsufficientFundsException(String message) {
		super(message);
		
	}

}
