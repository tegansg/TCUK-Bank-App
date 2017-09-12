package io.zipcoder.repositories;

import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
