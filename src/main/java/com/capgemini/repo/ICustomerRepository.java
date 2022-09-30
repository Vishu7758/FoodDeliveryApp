package com.capgemini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Restaurant;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, String> {
	@Query("select c from Customer c")
	List<Customer> viewAllCustomers(Restaurant rest);
}