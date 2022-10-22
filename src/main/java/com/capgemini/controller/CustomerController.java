package com.capgemini.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Customer;
import com.capgemini.entities.Restaurant;
import com.capgemini.service.ICustomerService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.IRestaurantService;

@RestController
public class CustomerController {

	@Autowired
	private ICustomerService service;

	@Autowired
	private IRestaurantService restService;

	@Autowired
	private ILoginService loginService;

	@PostMapping("/registerCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		Customer saved = null;
		saved = service.addCustomer(customer);
		return new ResponseEntity<Customer>(saved, HttpStatus.OK);
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		Customer newCustomer = service.updateCustomer(customer);
		if (newCustomer == null) {
			return new ResponseEntity("Sorry! Customer not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<Customer> removeCustomer(@RequestBody Customer customer, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		Customer delCustomer = service.removeCustomer(customer);
		if (delCustomer == null) {
			return new ResponseEntity("Sorry! Customer not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(delCustomer, HttpStatus.OK);
	}

	@GetMapping("/viewCustomer/{customerId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable String customerId, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		Customer findCustomer = service.viewCustomerById(customerId);
		Customer showCustomer = service.viewCustomer(findCustomer);
		if (showCustomer == null) {
			return new ResponseEntity("Sorry! Customer not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(showCustomer, HttpStatus.OK);
	}

	@GetMapping("/viewallCustomers/{restaurantId}")
	public ResponseEntity<List<Customer>> viewAllCustomers(@PathVariable String restaurantId,
			HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Restaurant rest = restService.viewRestaurant(restaurantId);
		List<Customer> list = service.viewAllCustomer(rest);
		if (list == null || list.isEmpty()) {
			return new ResponseEntity("Sorry! Customer not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}

}