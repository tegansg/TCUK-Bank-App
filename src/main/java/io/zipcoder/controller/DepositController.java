package io.zipcoder.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.domain.Customer;
import io.zipcoder.domain.Deposit;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.CustomerRepository;
import io.zipcoder.repositories.DepositRepository;

@RestController
public class DepositController {
	
	@Inject
	private DepositRepository depositRepository;
	
    @RequestMapping(value = "/deposit/{depositId}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable long depositId){
    	if(!depositRepository.exists(depositId)) {
    		return new ResponseEntity<>("Deposit does not exist", HttpStatus.NOT_FOUND);
    	} else {
            Deposit deposit = depositRepository.findOne(depositId);
            return new ResponseEntity<>(deposit, HttpStatus.OK);
    	}
    	
    }
    
    @RequestMapping(value = "/deposit/{depositId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable long depositId){
    	if(!depositRepository.exists(depositId)){
    		return new ResponseEntity<>("Deposit does not exist", HttpStatus.NOT_FOUND);
    	} else {
    		depositRepository.save(deposit);
    		return new ResponseEntity<>(deposit, HttpStatus.OK);
    	}
    }
    
    @RequestMapping(value = "/deposit/{depositId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable long depositId) {
    	if(!depositRepository.exists(depositId)) {
    		return new ResponseEntity<>("Deposit does not exist", HttpStatus.NOT_FOUND);
    	} else {
    		Deposit deposit = depositRepository.findOne(depositId);
    		depositRepository.delete(deposit);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    
    }

    
    
}
