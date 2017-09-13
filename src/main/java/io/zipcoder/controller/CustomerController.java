package io.zipcoder.controller;

import io.zipcoder.domain.Account;
import io.zipcoder.domain.Bill;
import io.zipcoder.domain.Customer;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.BillRepository;
import io.zipcoder.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
public class CustomerController {

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private AccountRepository accountRepository;
    @Inject
    private BillRepository billRepository;

    private Set<Customer> customerList;

    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable long id){
        Customer c = customerRepository.findOne(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerId}/bills", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBillsbyCustomer(@PathVariable Long customerId){

        Iterable<Bill> bills = billRepository.findBillsByAccount(customerId);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAccounts(long customerId) {

        Iterable<Account> accounts = accountRepository.findAccountsByCustomer(customerId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@PathVariable long customerId, @RequestBody Account account) {

        Customer c = customerRepository.findOne(customerId);
        account.setCustomer(c);
        accountRepository.save(account);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        Iterable<Customer> allCustomers = customerRepository.findAll();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){

        customerRepository.save(customer);

        URI newCustomerUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/customers/{id}") //?
                .buildAndExpand(customer.getId())
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newCustomerUri);

        return new ResponseEntity<>(newCustomerUri, HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
//    public ResponseEntity updateCustomer(@RequestBody Customer customer, @PathVariable long id){
//
//        if(customerRepository.exists(id)){
//            Customer c = customerRepository.findOne(id);
//            c.setFirst_name(customer.getFirst_name());
//            c.setLast_name(customer.getLast_name());
//            c.setAddress(customer.getAddress());
//            customerRepository.save(c);
//            return new ResponseEntity<>(c, HttpStatus.OK);
//        } else {
//            customerRepository.save(customer);
//            return new ResponseEntity<>(customer, HttpStatus.CREATED);
//        }
//    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable long id) {

        if (!customerRepository.exists(id)) {
            return new ResponseEntity<>("Customer does not exist", HttpStatus.NOT_FOUND);
        } else {
            Customer c = customerRepository.findOne(id);

            c.setFirst_name(customer.getFirst_name());
            c.setLast_name(customer.getLast_name());
            c.setAddress(customer.getAddress());
            customer = c;

            customerRepository.save(c);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }
}
