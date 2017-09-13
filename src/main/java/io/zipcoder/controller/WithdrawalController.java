package io.zipcoder.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.domain.Withdrawal;
import io.zipcoder.repositories.WithdrawalRepository;

@RestController
public class WithdrawalController {
	
	@Inject
	private WithdrawalRepository withdrawalRepository;
	
	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
	public ResponseEntity<?> getWithdrawal(@PathVariable long withdrawalId) {
		ResponseEntity<?> response = null;
		if(!withdrawalRepository.exists(withdrawalId)){
			response = new ResponseEntity<>("Error fetching ID", HttpStatus.NOT_FOUND);
		} else {
			Withdrawal withdrawal = withdrawalRepository.findOne(withdrawalId);
			response = new ResponseEntity<Withdrawal>(withdrawal, HttpStatus.OK);
		}
		return response;

	}
	
	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable long withdrawalId) {
		ResponseEntity<?> response=null;
		if (!withdrawalRepository.exists(withdrawalId)){
			response=new ResponseEntity<>("Wtihdrawal Id does not exist", HttpStatus.NOT_FOUND);
		}
		else{
			withdrawalRepository.save(withdrawal);
			response=new ResponseEntity<>("Accepted withdrawal", HttpStatus.ACCEPTED);
		}
		
		return response;

	}

	
	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWithdrawal(@PathVariable long withdrawalId) {
		ResponseEntity<?> response=null;
		if(!withdrawalRepository.exists(withdrawalId)){
			response = new ResponseEntity<>("Id does not exist", HttpStatus.NOT_FOUND);
		} else {
			withdrawalRepository.delete(withdrawalId);
			response = new ResponseEntity<>("Id has been deleted", HttpStatus.NO_CONTENT);
		}
		return response;
		
		

	}	

}
