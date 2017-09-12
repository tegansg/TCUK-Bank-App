package io.zipcoder.repositories;

import org.springframework.data.repository.CrudRepository;

import io.zipcoder.domain.Bill;

public interface BillRepository extends CrudRepository<Bill, Long>{

}
