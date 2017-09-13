package io.zipcoder.controller;

import java.util.List;

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
import io.zipcoder.domain.Deposit;
import io.zipcoder.domain.Withdrawal;
import io.zipcoder.repositories.AccountRepository;
import io.zipcoder.repositories.BillRepository;
import io.zipcoder.repositories.CustomerRepository;
import io.zipcoder.repositories.DepositRepository;
import io.zipcoder.repositories.WithdrawalRepository;

@RestController
public class AccountController {

	@Inject
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	private BillRepository billRepository;
	private DepositRepository depositRepository;
	private WithdrawalRepository withdrawalRepository;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAccounts() {
		List<Account> allAccounts=(List<Account>)accountRepository.findAll();
		return new ResponseEntity<List>(allAccounts, HttpStatus.OK);

	}

	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@PathVariable long accountId) {
		Account account = accountRepository.findOne(accountId);
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}
//how to go from customer to account
//	@RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.GET)
//	public ResponseEntity<?> getAllAccounts(@PathVariable long id) {
//		ArrayList<Account> allAccounts=new ArrayList<Account>(customerRepository.findOne(id).getAccounts();
//)
//		return new ResponseEntity<Account>(allAccounts, HttpStatus.OK);
//
//	}
//	@RequestMapping(value = "/customers/{customerId}/accounts", method = RequestMethod.POST)
//	public ResponseEntity<?> createAccount(@PathVariable Account account) {
//		Account newAccount=accountRepository.save(account);
//		return new ResponseEntity<Account>(newAccount, HttpStatus.CREATED);
//
//	}
	
	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@RequestBody Account account, @PathVariable long accountId) {
		ResponseEntity response=null;
		//do they need to enter account?
		if (!accountRepository.exists(accountId)){
			response=new ResponseEntity<Account>(HttpStatus.CREATED);
		}
		else{
			response=new ResponseEntity<Account>(HttpStatus.OK);
		}
		accountRepository.save(account);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@PathVariable long accountId) {
		Account account=accountRepository.findOne(accountId);
		accountRepository.delete(accountId);
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}	
	
    @RequestMapping(value = "/accounts/{accountId}/customer", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByAccount(@PathVariable long accountId){
        Account a = accountRepository.findOne(accountId);
        Customer c = a.getCustomer();
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    
	@RequestMapping(value = "/accounts/{accountId}/bills", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBillsByAccount(@PathVariable long accountId){
		List<Bill> billList= (List<Bill>)billRepository.findAll();
		return new ResponseEntity<>(billList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/{accountId}/bills", method = RequestMethod.POST)
	public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable long accountId){
		if (!accountRepository.exists(accountId)){
			return new ResponseEntity<String>("Account does not exist", HttpStatus.NOT_FOUND);
		}else{
			bill.setAccount_id(accountId);
			billRepository.save(bill);
		}
		return new ResponseEntity<>(bill, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.GET)
	public ResponseEntity<?> getAllDepositsByAccount(@PathVariable long accountId){
		List<Deposit> depositList = (List<Deposit>) depositRepository.findAll();
		return new ResponseEntity<>(depositList, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/accounts/{accountId}/deposits", method = RequestMethod.POST)
	public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit, @PathVariable long accountId){
		if (!accountRepository.exists(accountId)){
			return new ResponseEntity<String>("Account does not exist", HttpStatus.NOT_FOUND);
		}else{
			depositRepository.save(deposit);
		}
		return new ResponseEntity<>(deposit, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/accounts/{accountId}/withdrawals", method = RequestMethod.GET)
	public ResponseEntity<?> getAllWithdrawalsByAccount(@PathVariable long accountId){
		List<Withdrawal> withdrawalList = (List<Withdrawal>) withdrawalRepository.findAll();
		return new ResponseEntity<>(withdrawalList, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/accounts/{accountId}/withdrawals", method = RequestMethod.POST)
	public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable long accountId){
		if (!accountRepository.exists(accountId)){
			return new ResponseEntity<String>("Account does not exist", HttpStatus.NOT_FOUND);
		}else{
			withdrawalRepository.save(withdrawal);
		}
		return new ResponseEntity<>(withdrawal, HttpStatus.ACCEPTED);
	}

	
}
