package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Bill;
import com.capgemini.service.IBillService;

@RestController
@RequestMapping("/bill")
public class IBillController {

	@Autowired
	IBillService service;

	@PostMapping(value = "/add")
	public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
		Bill saved = null;
		saved = service.addBill(bill);
		return new ResponseEntity<Bill>(saved, HttpStatus.OK);
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Bill> viewBill(@RequestBody Bill bill) {
		Bill showBill = service.viewBill(bill);
		if (showBill == null) {
			return new ResponseEntity("Bill not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bill>(showBill, HttpStatus.OK);
	}

	@GetMapping(value = "/get/{custId}")
	public ResponseEntity<List<Bill>> viewBillUsingCustomerId(@PathVariable String custId) {
		List<Bill> bills = service.viewBills(custId);
		if (bills == null) {
			return new ResponseEntity("Bill not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Bill> updateBill(@RequestBody Bill bill) {
		Bill newBill = service.updateBill(bill);
		if (newBill == null) {
			return new ResponseEntity("Bill not available for Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bill>(newBill, HttpStatus.OK);
	}

	@DeleteMapping(value = "/del")
	public ResponseEntity<Bill> removeBill(@RequestBody Bill bill) {
		Bill deletedBill = service.removeBill(bill);
		if (deletedBill == null) {
			return new ResponseEntity("Bill is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bill>(deletedBill, HttpStatus.OK);
	}
}