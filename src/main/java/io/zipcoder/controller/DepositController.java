package io.zipcoder.controller;

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
	private DepositRepository depositRepository;
	//private AccountRepository accountRepository;
	
//	@Autowired //"Enumbody"

	
    public DepositController(DepositRepository depositRepository){
        this.depositRepository = depositRepository;
    }
	
    @RequestMapping(value = "/deposit/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable long id){
        Deposit d = depositRepository.findOne(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deposit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable long id){

        depositRepository.save(deposit);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deposit/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable long id){
    	depositRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
    	
    }
}
