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
import io.zipcoder.domain.Withdrawal;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.WithdrawalRepository;

@RestController
public class WithdrawalController {

	@Inject
	private WithdrawalRepository withdrawalRepository;

	@Inject
	private AccountRepository accountRepository;

	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.GET)
	public ResponseEntity<?> getWithdrawal(@PathVariable long withdrawalId) {
		ResponseEntity<?> response = null;
		if (!withdrawalRepository.exists(withdrawalId)) {
			response = new ResponseEntity<>("Error fetching ID", HttpStatus.NOT_FOUND);
		} else {
			Withdrawal withdrawal = withdrawalRepository.findOne(withdrawalId);
			response = new ResponseEntity<Withdrawal>(withdrawal, HttpStatus.OK);
		}
		return response;

	}

	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable long withdrawalId) { // get
																														// original
																														// withdraw
																														// and
																														// update.
																														// also
																														// update
																														// account
		ResponseEntity<?> response = null;
		if (!withdrawalRepository.exists(withdrawalId)) {
			response = new ResponseEntity<>("Wtihdrawal Id does not exist", HttpStatus.NOT_FOUND);
		} else {

			withdrawal.setPayer_id(withdrawalRepository.findOne(withdrawalId).getPayer_id());
			Account account = accountRepository.findOne(withdrawal.getPayer_id());

			if (account.decreaseBalance(withdrawal.getAmount())) {
				response = new ResponseEntity<>("Accepted withdrawal", HttpStatus.ACCEPTED);
			} else {
				response = new ResponseEntity<>("not enough money", HttpStatus.BAD_REQUEST);
			}

		}

		return response;
	}

	@RequestMapping(value = "/withdrawals/{withdrawalId}", method = RequestMethod.DELETE) // reverse
																							// whats
																							// happened
																							// in
																							// account
																							// and
																							// THEN
																							// delete
	public ResponseEntity<?> deleteWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable long withdrawalId) {
		ResponseEntity<?> response = null;
		if (!withdrawalRepository.exists(withdrawalId)) {
			response = new ResponseEntity<>("Id does not exist", HttpStatus.NOT_FOUND);
		} else {

			withdrawal.setPayer_id(withdrawalRepository.findOne(withdrawalId).getPayer_id());
			Account account = accountRepository.findOne(withdrawal.getPayer_id());
			if (account.increaseBalance(withdrawal.getAmount())) {
				withdrawalRepository.delete(withdrawalId);
				response = new ResponseEntity<>("Id has been deleted and balance updated", HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<>("not enough money", HttpStatus.BAD_REQUEST);
			}

		}
		return response;
	}

}
