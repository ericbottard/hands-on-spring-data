package org.springframework.data.samples._01_jpa.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomersRepository extends PagingAndSortingRepository<Customer,Long>, QueryDslPredicateExecutor<Customer> {

	@Query("select c from Customer c where length(c.firstName) = length(c.lastName) and length(c.firstName) = :l")
	Iterable<Customer> findWithEqualLengthNames(@Param("l") int length);

}
