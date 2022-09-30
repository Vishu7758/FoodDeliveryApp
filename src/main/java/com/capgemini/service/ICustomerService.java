package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Customer;

public interface ICustomerService {
	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	Customer removeCustomer(Customer customer);

	Customer viewCustomer(Customer customer);

	List<Customer> viewAllCustomer();

}