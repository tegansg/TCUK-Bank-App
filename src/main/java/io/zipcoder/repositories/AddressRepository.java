package io.zipcoder.repositories;

import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

}
