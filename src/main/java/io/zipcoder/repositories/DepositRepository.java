package io.zipcoder.repositories;

import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long>{

}
