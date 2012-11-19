package org.springframework.data.samples._01_jpa.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomersRepository extends PagingAndSortingRepository<Customer,Long> {

}
