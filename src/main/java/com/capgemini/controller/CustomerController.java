package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.entities.Customer;
import com.capgemini.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService service;
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		Customer saved=null;
		saved=service.addCustomer(customer);
		return new ResponseEntity<Customer>(saved, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		Customer newCustomer=service.updateCustomer(customer);
		if(newCustomer==null) {
			return new ResponseEntity("Sorry! Customer not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(newCustomer,HttpStatus.OK); 
	}
	
	@DeleteMapping
	public ResponseEntity<Customer> removeCustomer(@RequestBody Customer customer){
		Customer delCustomer=service.removeCustomer(customer);
		if(delCustomer==null) {
			return new ResponseEntity("Sorry! Customer not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(delCustomer, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Customer> viewCustomer(@RequestBody Customer customer){
		Customer showCustomer=service.viewCustomer(customer);
		if(showCustomer==null) {
			return new ResponseEntity("Sorry! Customer not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	

}
