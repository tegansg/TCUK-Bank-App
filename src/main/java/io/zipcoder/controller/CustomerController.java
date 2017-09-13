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


//    @RequestMapping(value = "/customers/{customerId}/bills", method = RequestMethod.GET)
//	public ResponseEntity<?> getAllBillsbyCustomer(@PathVariable Long customerId){
//
//        // for each account in the list of accounts we want to find the associated accountId
//        // then we can find the bills associated with that account and therefore, that customer
//
//        Iterable<Account> accounts = accountRepository.findAccountsByCustomer(customerId);
//        Iterator<Account> iterator = accounts.iterator();
//
//        while (iterator.hasNext()){
//            Account account = iterator.next();
//            long accountId = account.getId();
//
//        }
//
//		return new ResponseEntity<>(bills, HttpStatus.OK);
//	}

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

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCustomer(@RequestBody Customer customer, @PathVariable long id){

        Customer c = customerRepository.findOne(id);

        if(c.getId() == id){
            c.setFirst_name(customer.getFirst_name());
            c.setLast_name(customer.getLast_name());
            customerRepository.save(c);
        } else {
            createCustomer(customer);
            return new ResponseEntity(customer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
