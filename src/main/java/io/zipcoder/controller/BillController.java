package io.zipcoder.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.repositories.BillRepository;

@RestController
public class BillController {
	
	@Inject
	BillRepository billRepository;
	
	@RequestMapping(value = "/accounts/{accountId}/bills", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBillsbyAccount(@PathVariable Long account_id){
		
		
		return null;
	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBillbyId(@PathVariable Long bill_id){
		billRepository.findOne(bill_id);
		
		return null;
	}
	
	@RequestMapping(value = "/customers/{customerId}/bills", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBillsbyCustomer(@PathVariable Long customer_id){
		
		
		return null;
	}
	
	@RequestMapping(value = "/accounts/{accountId}/bills", method = RequestMethod.POST)
	public ResponseEntity<?> createBill(@PathVariable Long account_id){
		
		
		return null;
	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBill(@PathVariable Long bill_id){
		
		
		return null;
	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBill(@PathVariable Long bill_id){
		
		
		return null;
	}
	

	

}
