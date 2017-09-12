package io.zipcoder.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.domain.Account;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.CustomerRepository;

@RestController
public class AccountController {

	@Inject
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAccounts() {
		List<Account> allAccounts=(List<Account>)accountRepository.findAll();
		return new ResponseEntity<List>(allAccounts, HttpStatus.OK);

	}

	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@PathVariable long id) {
		Account account = accountRepository.findOne(id);
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}
//how to go from customer to account
//	@RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.GET)
//	public ResponseEntity<?> getAllAccounts(@PathVariable long id) {
//		ArrayList<Account> allAccounts=new ArrayList<Account>(customerRepository.findOne(id).getAccounts();
//)
//		return new ResponseEntity<Account>(allAccounts, HttpStatus.OK);
//
//	}
//	@RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.POST)
//	public ResponseEntity<?> createAccount(@PathVariable Account account) {
//		Account newAccount=accountRepository.save(account);
//		return new ResponseEntity<Account>(newAccount, HttpStatus.CREATED);
//
//	}
	
	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@RequestBody Account account, @PathVariable long id) {
		ResponseEntity response=null;
		//do they need to enter account?
		if (!accountRepository.exists(id)){
			response=new ResponseEntity<Account>(HttpStatus.CREATED);
		}
		else{
			response=new ResponseEntity<Account>(HttpStatus.OK);
		}
		accountRepository.save(account);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.PUT)
	public ResponseEntity<?> deleteAccount(@PathVariable long id) {
		Account account=accountRepository.findOne(id);
		accountRepository.delete(id);
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}	
	
	
}
