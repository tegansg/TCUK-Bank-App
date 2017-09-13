package io.zipcoder.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long>{
	
	
    String query = "SELECT w.* " +
    				"FROM Withdrawal w " +
    				"WHERE w.ACCOUNT_ID = ?1";

    @Query(value = query, nativeQuery = true)
	public Iterable<Withdrawal> getWithdrawalsByAccount();
}
