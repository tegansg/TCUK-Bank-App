package io.zipcoder.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.domain.Bill;
import io.zipcoder.exceptions.ResourceNotFoundException;
import io.zipcoder.repositories.BillRepository;


@RestController
public class BillController {
	
	@Inject
	BillRepository billRepository;
	
//	@RequestMapping(value = "/accounts/{accountId}/bills", method = RequestMethod.GET)
//	public ResponseEntity<?> getAllBillsbyAccount(@PathVariable Long account_id){
//		
//		
//		return null;
//	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBillbyId(@PathVariable Long billId){
		
		//if(billRepository.exists(bill_Id)){
			verifyBill(billId);
			Bill bill = billRepository.findOne(billId);
			return new ResponseEntity<>(bill , HttpStatus.OK);
		//}
		//return new ResponseEntity<>("No bill for this ID", HttpStatus.NOT_FOUND);
	}
	
//	@RequestMapping(value = "/customers/{customerId}/bills", method = RequestMethod.GET)
//	public ResponseEntity<?> getAllBillsbyCustomer(@PathVariable Long customer_id){
//		
//		
//		return null;
//	}
	
//	@RequestMapping(value = "/accounts/{accountId}/bills", method = RequestMethod.POST)
//	public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long account_id){
//		
//		// if account_id doesn't exist, return 404 error
//		//else create bill to account_id
//		billRepository.save(bill);
//		
//		return new ResponseEntity<>("Accepted bill modification", HttpStatus.ACCEPTED);
//	}
	
	@RequestMapping(value = "/bills", method = RequestMethod.POST)
	public ResponseEntity<?> createBill(@RequestBody Bill bill){
	
			billRepository.save(bill);//"Accepted bill modification", 
			return new ResponseEntity<>(bill, HttpStatus.ACCEPTED);
		}
	
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
		
		if(billRepository.exists(billId)){		
			billRepository.save(bill);
			return new ResponseEntity<>("Accepted bill modification", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("No bill for this ID", HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBill(@PathVariable Long billId){
		
		if(billRepository.exists(billId)){
			Bill bill = billRepository.findOne(billId);
			billRepository.delete(bill);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("No bill for this ID", HttpStatus.NOT_FOUND);
	}
	
	public void verifyBill(Long bill_id) {
		//if doesn't exist throws exception
		if(!billRepository.exists(bill_id)){
			throw new ResourceNotFoundException();
		}
	
	}
	
}
