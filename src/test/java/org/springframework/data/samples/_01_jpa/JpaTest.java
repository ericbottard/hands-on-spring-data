/*
* Copyright 2012 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springframework.data.samples._01_jpa;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.samples._01_jpa.domain.Customer;
import org.springframework.data.samples._01_jpa.domain.QCustomer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext-jpa.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class JpaTest {

	@Inject
	private CustomersRepository repository;

	@Test
	public void should_find_one_customer_by_id() {
        // Find the customer with id 42
		Customer customer = null; // TODO, using repository

        // Check it matches the given email
		assertThat(customer.getEmail()).isEqualTo(
				"CAROLYN.PEREZ@sakilacustomer.org");
	}

	@Test
	public void should_find_all_customers() {
        // Find all customers
		Iterable<Customer> customers = null; // TODO, using repository
		assertThat(customers).hasSize(599);
	}

	@Test
	public void should_find_second_next_5_customers_sorted_by_last_name() {
        // Retrieve the 5-sized 2nd page of customers, sorted by last name in descending order
        PageRequest withPagination = null; // TODO

        Iterable<Customer> customers = null; // TODO, using the PageRequest object created above

        // Check their last names
        Iterable<String> lastNames = extractProperty("lastName", String.class).from(customers);
        assertThat(lastNames)
				.containsOnly( //
						"WREN", "WOODS", "WOOD", "WOFFORD", "WINDHAM" //
				);
	}

	@Test
	public void can_use_explicit_queries() {
        // Find customers whose first and last names are both 8 chars long
		Iterable<Customer> customers = repository.findWithEqualLengthNames(8); // TODO using an annotated method

		assertThat(extractProperty("id", Long.class).from(customers))
				.containsOnly(514L, 362L, 290L, 207L, 165L);

	}

	@Test
	public void using_querydsl_specifications() {
		// Customers whose firstName starts with "Mar" (ignore case)
		BooleanExpression customerFirstNamePredicate = null; // TODO

		// Customers whose city address name is longer than 10 characters
		Predicate cityNamePredicate = null; // TODO

		// Find customers that satisfy BOTH predicates
		Iterable<Customer> customers = null; // TODO

		assertThat(extractProperty("email", String.class).from(customers))
				.containsOnly("MARSHALL.THORN@sakilacustomer.org",
						"MARVIN.YEE@sakilacustomer.org",
						"MARGIE.WADE@sakilacustomer.org",
						"MARION.SNYDER@sakilacustomer.org",
						"MARJORIE.TUCKER@sakilacustomer.org");
	}
}
