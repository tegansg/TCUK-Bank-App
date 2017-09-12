package io.zipcoder.repositories;

import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
