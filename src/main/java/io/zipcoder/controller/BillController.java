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
import io.zipcoder.domain.Bill;
import io.zipcoder.domain.Customer;
import io.zipcoder.repositories.BillRepository;


@RestController
public class BillController {
	
	@Inject
	private BillRepository billRepository;
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBillbyId(@PathVariable long billId){
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
	public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable long billId){
		ResponseEntity<?> response = null;
		if(!billRepository.exists(billId)){	
			return new ResponseEntity<String>("No bill for this ID", HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(HttpStatus.OK);
			Bill billNew = billRepository.findOne(billId);
			billNew.setId(billId);
			billRepository.save(billNew);
			//response = new ResponseEntity<>("Accepted bill modification", HttpStatus.ACCEPTED);
			response= new ResponseEntity<Bill>(bill, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value = "/bills/{billId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBill(@PathVariable long billId){
//		ResponseEntity<?> response = null;
//		if(!billRepository.exists(billId)){
//			response = new ResponseEntity<>("No bill for this ID", HttpStatus.NOT_FOUND);
//		} else {
//			
//			billRepository.delete(billId);
//			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return response;
			Bill bill = billRepository.findOne(billId);
			billRepository.delete(billId);
			return new ResponseEntity<Bill>(bill, HttpStatus.OK);

		}
	}
	
	

