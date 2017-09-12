package io.zipcoder.repositories;

import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Withdrawal;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long>{

}
