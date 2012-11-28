package org.springframework.data.samples._01_jpa.domain;

import javax.persistence.*;

import com.mysema.query.annotations.QueryInit;

@Entity
public class Customer {
	
	@Id
	@Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	@QueryInit("*")
	private Address address;
	
	

}
