package io.zipcoder.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long>{

	   String query = "SELECT d.* " +
				"FROM Deposit d " +
				"WHERE d.ACCOUNT_ID = ?1";

	   @Query(value = query, nativeQuery = true)
	   public Iterable<Deposit> getDepositsByAccount();

}
