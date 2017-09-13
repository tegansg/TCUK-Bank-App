package io.zipcoder.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Bill;


public interface BillRepository extends CrudRepository<Bill, Long>{

    String query = "SELECT a.* " +
            "FROM Bill b, Account a " +
            "WHERE a.ACCOUNT_ID = ?1 " +
            "AND a.BILL_ID = b.BILL_ID";

    @Query(value = query, nativeQuery = true)
    public Iterable<Bill> findBillsByAccount(long accountId);
}
