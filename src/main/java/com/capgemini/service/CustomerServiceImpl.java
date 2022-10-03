package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository repository;

	@Override
	public Customer addCustomer(Customer customer) {
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer updated = null;
		if (repository.existsById(customer.getCustomerId()))
			updated = repository.save(customer);
		return updated;
	}

	@Override
	public Customer removeCustomer(Customer customer) {
		Customer delCustomer = customer;
		if (repository.existsById(customer.getCustomerId())) {
			repository.delete(customer);
			return delCustomer;
		}

		return null;
	}

	@Override
	public Customer viewCustomer(Customer customer) {
		return repository.findById(customer.getCustomerId()).get();
	}

	@Override
	public List<Customer> viewAllCustomer(Restaurant rest) {
		return repository.viewAllCustomers(rest.getRestaurantId());
	}

}