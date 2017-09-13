package io.zipcoder.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

    String query = "SELECT a.* " +
                    "FROM Account a, Customer c " +
                    "WHERE c.CUSTOMER_ID = ?1 " +
                    "AND c.ACCOUNT_ID = a.ACCOUNT_ID";

    @Query(value = query, nativeQuery = true)
    public Iterable<Account> findAccountsByCustomer(long customerId);
}
