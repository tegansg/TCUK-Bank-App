package io.zipcoder.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Bill;


public interface BillRepository extends CrudRepository<Bill, Long>{
	
	String query = "SELECT b.* " +
					"FROM Bill b " +
					"WHERE b.ACCOUNT_ID = ?1 " ;

    @Query(value = query, nativeQuery = true)
    public Iterable<Bill> findBillsByAccount(long accountId);
}
