package org.springframework.data.samples._01_jpa.domain;

import javax.persistence.*;

@Entity
public class Country {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name="country_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="country")
	private String name;

}
