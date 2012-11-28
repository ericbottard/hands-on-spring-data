package org.springframework.data.samples._01_jpa.domain;

import javax.persistence.*;

import com.mysema.query.annotations.QueryInit;

@Entity
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="city_id")
	@QueryInit("*")
    private City city;
    private String district;
    @Column(name = "postal_code")
    private String postalCode;
    private String address;
    @Column(name = "address2")
    private String addressExtension;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressExtension() {
        return addressExtension;
    }

    public void setAddressExtension(String addressExtension) {
        this.addressExtension = addressExtension;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
