package com.capgemini.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	private String customerId;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String mobileNumber;
	private boolean isActive = true;
	private String email;
	@Embedded
	private Address address;

	public Customer() {
		this.isActive = true;
	}

	public Customer(String customerId, String firstName, String lastName, int age, String gender, String mobileNumber,
			String email, Address address) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.address = address;
	}

}