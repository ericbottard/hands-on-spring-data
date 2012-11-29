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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.samples._01_jpa.domain.Customer;
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
        Customer customer = repository.findOne(42L);
        assertThat(customer.getEmail()).isEqualTo("CAROLYN.PEREZ@sakilacustomer.org");
    }

    @Test
    public void should_find_all_customers() {
        Iterable<Customer> customers = repository.findAll();
        assertThat(customers).hasSize(599);
    }

    @Test
    public void should_find_second_next_5_customers_sorted_by_last_name() {
        Iterable<Customer> customers = repository.findAll(new PageRequest(1, 5, Sort.Direction.DESC, "lastName"));
        assertThat(extractProperty("lastName", String.class).from(customers)).containsOnly( //
            "WREN", "WOODS", "WOOD", "WOFFORD", "WINDHAM"                                   //
        );
    }
}
