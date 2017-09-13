package io.zipcoder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
//	public void verifyBill(Long bill_id) {
//		//if doesn't exist throws exception
//		if(!billRepository.exists(bill_id)){
//			throw new ResourceNotFoundException();
//		}

}
