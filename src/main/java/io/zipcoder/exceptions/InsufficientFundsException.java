package io.zipcoder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
