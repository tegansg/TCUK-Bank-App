package io.zipcoder.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.zipcoder.domain.Bill;
import io.zipcoder.repositories.BillRepository;


@RestController
public class BillController {
	
	@Inject
	BillRepository billRepository;
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBillbyId(@PathVariable Long billId){
		ResponseEntity<?> response = null;
		if(!billRepository.exists(billId)){
			response =  new ResponseEntity<>("Error fetching bill", HttpStatus.NOT_FOUND);
		} else {
			Bill bill = billRepository.findOne(billId);
			response = new ResponseEntity<>(bill , HttpStatus.OK);
		}
		return response;
	}
	
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
		ResponseEntity<?> response = null;
		if(!billRepository.exists(billId)){	
			response = new ResponseEntity<>("No bill for this ID", HttpStatus.NOT_FOUND);
		} else {
			billRepository.save(bill);
			response = new ResponseEntity<>("Accepted bill modification", HttpStatus.ACCEPTED);
		}
		return response;


	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBill(@PathVariable Long billId){
		ResponseEntity<?> response = null;
		if(!billRepository.exists(billId)){
			response = new ResponseEntity<>("No bill for this ID", HttpStatus.NOT_FOUND);
		} else {
			Bill bill = billRepository.findOne(billId);
			billRepository.delete(bill);
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
		
	}
	

	
}
	

