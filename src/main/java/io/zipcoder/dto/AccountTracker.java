package io.zipcoder.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import io.zipcoder.domain.Account;
import io.zipcoder.domain.Customer;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.CustomerRepository;

public class AccountTracker {
	private Account account;
	private Customer customer;
	private AccountRepository accounts;
	private CustomerRepository customers;

	ArrayList<Account> accountsList = new ArrayList<>();

	ArrayList<Account> customersList = new ArrayList<>();
//	long accountId = account.getId();
//	long customerId = customer.getId();
//	HashMap<Account, Customer> tracker = new HashMap<Account, Customer>();
//
//	public void getAllAccounts() {
//		accountsList.add((Account) accounts.findAll());
//	}
//
//	public void getAllCustomers() {
//		customersList.add((Customer) customers.findAll());
//	}
//
//	public HashMap createList() {
//		for (int i = 0; i < accountsList.size(); i++) {
//			tracker.put(accountsList.get(i), customersList.get(i));
//		}
//	}
//
//	public void findAccount(long customerId) {
//		tracker.putAll(if ());
//
//	}
}
