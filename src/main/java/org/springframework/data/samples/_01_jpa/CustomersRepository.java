package org.springframework.data.samples._01_jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.samples._01_jpa.domain.Customer;

public interface CustomersRepository extends PagingAndSortingRepository<Customer,Long> {

}
