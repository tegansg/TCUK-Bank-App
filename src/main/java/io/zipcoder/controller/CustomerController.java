package io.zipcoder.controller;

// TODO below

import io.zipcoder.domain.Account;
import io.zipcoder.domain.Customer;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Set;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
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
// commented out and moved to AccountController
//    @RequestMapping(value = "/accounts/{accountId}/customer", method = RequestMethod.GET)
//    public ResponseEntity<?> getCustomerByAccount(@PathVariable long accountId){
//        Account a = accountRepository.findOne(accountId);
//        // Customer c = a.getCustomer;
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//TODO fill this in
    	@RequestMapping(value = "/customers/{customerId}/bills", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBillsbyCustomer(@PathVariable Long customer_id){

        //Write an SQL query in BillRepo

		return null;
	}

    @RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAccounts(long customerId) {

        Iterable<Account> accounts = accountRepository.findAccountsByCustomer(customerId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@PathVariable long customerId, @PathVariable Account account) {

        Customer c = customerRepository.findOne(customerId);
        account.setCustomer(c);
        account = accountRepository.save(account);
		return new ResponseEntity<>(account, HttpStatus.CREATED);

	}

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        Iterable<Customer> allCustomers = customerRepository.findAll();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){

        customer = customerRepository.save(customer);

        URI newCustomerUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/customers/{id}") //?
                .buildAndExpand(customer.getId())
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newCustomerUri);

        return new ResponseEntity<>(newCustomerUri, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCustomer(@RequestBody Customer customer, @PathVariable long id){

        Customer c = customerRepository.findOne(id);

        if(c.getId() == id){
            c.setFirst_name(customer.getFirst_name());
            c.setLast_name(customer.getLast_name());
        } else {
            createCustomer(customer);
            return new ResponseEntity(customer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
