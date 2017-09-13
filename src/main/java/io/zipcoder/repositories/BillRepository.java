package io.zipcoder.repositories;

//import io.zipcoder.domain.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Bill;


public interface BillRepository extends CrudRepository<Bill, Long>{

//    String query = "SELECT a.* " +
//            "FROM Bill b, Account a " +
//            "WHERE a.ACCOUNT_ID = ?1 " +
//            "AND a.BILL_ID = b.BILL_ID";

    String query = "SELECT b.* FROM Bill b " +
            "WHERE b.ACCOUNT_ID = " +
            "(SELECT a.ACCOUNT_ID " +
            "FROM Account a, Customer c " +
            "WHERE c.CUSTOMER_ID = ?1)";

    @Query(value = query, nativeQuery = true)
    public Iterable<Bill> findBillsByAccount(long accountId);
}
