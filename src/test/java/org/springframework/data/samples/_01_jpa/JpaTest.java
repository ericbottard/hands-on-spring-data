package org.springframework.data.samples._01_jpa;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.samples._01_jpa.domain.Customer;
import org.springframework.data.samples._01_jpa.domain.CustomersRepository;
import org.springframework.data.samples._01_jpa.domain.QCustomer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext-jpa.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class JpaTest {

	@Inject
	private CustomersRepository repository;

	@Test
	public void should_find_one_customer_by_id() {
		Customer customer = repository.findOne(42L);
		assertThat(customer.getEmail()).isEqualTo(
				"CAROLYN.PEREZ@sakilacustomer.org");
	}

	@Test
	public void should_find_all_customers() {
		Iterable<Customer> customers = repository.findAll();
		assertThat(customers).hasSize(599);
	}

	@Test
	public void should_find_second_next_5_customers_sorted_by_last_name() {
		Iterable<Customer> customers = repository.findAll(new PageRequest(1, 5,
				Sort.Direction.DESC, "lastName"));
		assertThat(extractProperty("lastName", String.class).from(customers))
				.containsOnly( //
						"WREN", "WOODS", "WOOD", "WOFFORD", "WINDHAM" //
				);
	}

	@Test
	public void can_use_explicit_queries() {
		Iterable<Customer> customers = repository.findWithEqualLengthNames(8);

		assertThat(extractProperty("id", Long.class).from(customers))
				.containsOnly(514L, 362L, 290L, 207L, 165L);

	}

	@Test
	public void using_querydsl_specifications() {

		// Customers whose firstName starts with "Mar" (ignore case)
		BooleanExpression customerFirstNamePredicate = QCustomer.customer.firstName
				.startsWithIgnoreCase("Mar");

		// Customers whose city address name is longer than 10 characters
		Predicate cityNamePredicate = QCustomer.customer.address.city.cityName
				.length().gt(10);

		// Find customers that satisfy BOTH predicates
		Iterable<Customer> customers = repository
				.findAll(customerFirstNamePredicate.and(cityNamePredicate));

		assertThat(extractProperty("email", String.class).from(customers))
				.containsOnly("MARSHALL.THORN@sakilacustomer.org",
						"MARVIN.YEE@sakilacustomer.org",
						"MARGIE.WADE@sakilacustomer.org",
						"MARION.SNYDER@sakilacustomer.org",
						"MARJORIE.TUCKER@sakilacustomer.org");
	}
}
