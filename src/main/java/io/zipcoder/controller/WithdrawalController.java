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
	public ResponseEntity<?> getWithdrawal(@PathVariable long id) {
		Withdrawal withdrawal = withdrawalRepository.findOne(id);
		return new ResponseEntity<Withdrawal>(withdrawal, HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable long id) {
		ResponseEntity response=null;
		if (!withdrawalRepository.exists(id)){
			response=new ResponseEntity<Withdrawal>(HttpStatus.CREATED);
		}
		else{
			response=new ResponseEntity<Withdrawal>(HttpStatus.OK);
		}
		withdrawalRepository.save(withdrawal);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWithdrawal(@PathVariable long id) {
		Withdrawal withdrawal=withdrawalRepository.findOne(id);
		withdrawalRepository.delete(id);
		return new ResponseEntity<Withdrawal>(withdrawal, HttpStatus.OK);

	}	

}
