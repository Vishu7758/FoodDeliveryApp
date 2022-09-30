package com.capgemini.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
	@Id
	private String customerId;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String mobileNumber;

	@Embedded
	private Address address;

	private String email;

}