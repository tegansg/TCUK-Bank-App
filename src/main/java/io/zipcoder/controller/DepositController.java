package io.zipcoder.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.domain.Account;
import io.zipcoder.domain.Deposit;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.DepositRepository;

@RestController
public class DepositController {

	@Inject
	private DepositRepository depositRepository;

	@Inject
	private AccountRepository accountRepository;

	@RequestMapping(value = "/deposit/{depositId}", method = RequestMethod.GET)
	public ResponseEntity<?> getId(@PathVariable long depositId) {
		if (!depositRepository.exists(depositId)) {
			return new ResponseEntity<>("Deposit does not exist", HttpStatus.NOT_FOUND);
		} else {
			Deposit deposit = depositRepository.findOne(depositId);
			return new ResponseEntity<>(deposit, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/deposit/{depositId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable long depositId) {
		ResponseEntity<?> response = null;
		if (!depositRepository.exists(depositId)) {
			return new ResponseEntity<>("Deposit does not exist", HttpStatus.NOT_FOUND);
		} else {

			deposit.setPayee_id(depositRepository.findOne(depositId).getPayee_id());
			Account account = accountRepository.findOne(deposit.getPayee_id());
			if (account.increaseBalance(deposit.getAmount())) {
				response = new ResponseEntity<>("Deposit successful, account updated", HttpStatus.OK);
			} else {
				response = new ResponseEntity<>("Deposit unsuccessful, account updated", HttpStatus.BAD_REQUEST);
			}
		}
		return response;
	}

	@RequestMapping(value = "/deposit/{depositId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDeposit(@RequestBody Deposit deposit, @PathVariable long depositId) {
		ResponseEntity<?> response = null;
		if (!depositRepository.exists(depositId)) {
			response = new ResponseEntity<>("Deposit does not exist", HttpStatus.NOT_FOUND);
		} else {

			deposit.setPayee_id(depositRepository.findOne(depositId).getPayee_id());
			Account account = accountRepository.findOne(deposit.getPayee_id());
			if (account.decreaseBalance(deposit.getAmount())) {
				depositRepository.delete(depositId);
				response = new ResponseEntity<>("Id deleted, account updated", HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<>("not enough money", HttpStatus.BAD_REQUEST);
			}

		}
		return response;
	}

}
