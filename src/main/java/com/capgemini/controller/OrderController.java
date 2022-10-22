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

import com.capgemini.entities.Login;
import com.capgemini.entities.OrderDetails;
import com.capgemini.service.ICustomerService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.IOrderService;
import com.capgemini.service.IRestaurantService;

@RestController
public class OrderController {

	@Autowired
	IOrderService service;
	@Autowired
	IRestaurantService resService;
	@Autowired
	ICustomerService custService;
	@Autowired
	ILoginService loginService;

	@PostMapping("/addOrder")
	public ResponseEntity<OrderDetails> addOrder(@RequestBody OrderDetails order, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// todo owner check
		OrderDetails saved = null;
		saved = service.addOrder(order);
		return new ResponseEntity<OrderDetails>(saved, HttpStatus.OK);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<OrderDetails> updateOrder(@RequestBody OrderDetails order, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// todo owner check
		OrderDetails newOrder = service.updateOrder(order);
		if (newOrder == null) {
			return new ResponseEntity("Sorry! OrderDetails not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderDetails>(newOrder, HttpStatus.OK);
	}

	@DeleteMapping("/removeOrder")
	public ResponseEntity<OrderDetails> removeOrder(@RequestBody OrderDetails order, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// todo owner check
		OrderDetails delOrder = service.removeOrder(order);
		if (delOrder == null) {
			return new ResponseEntity("Sorry! OrderDetails not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderDetails>(delOrder, HttpStatus.OK);
	}

	@GetMapping("/viewOrder/{orderId}")
	public ResponseEntity<OrderDetails> viewOrder(@PathVariable int orderId, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// todo owner check
		OrderDetails showOrder = service.viewOrder(service.viewOrderById(orderId));
		if (showOrder == null) {
			return new ResponseEntity("Sorry! OrderDetails not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderDetails>(showOrder, HttpStatus.OK);
	}

	@GetMapping("/viewAllOrders/{restaurantId}")
	public ResponseEntity<List<OrderDetails>> viewAllOrders(@PathVariable String restaurantId,
			HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// todo owner check
		Login currentUser = (Login) request.getSession().getAttribute("userDetails");
		if (!currentUser.isOwner()) {
			throw new IllegalArgumentException("Operation not allowed");
		}

		List<OrderDetails> resultSet = service.viewAllOrders(resService.viewRestaurant(restaurantId));
		if (resultSet == null) {
			return new ResponseEntity("Sorry! OrderDetails not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<OrderDetails>>(resultSet, HttpStatus.OK);
	}

	@GetMapping("/viewAllOrders/{customerId}")
	public ResponseEntity<List<OrderDetails>> viewAllOrdersByCustomer(@PathVariable String customerId,
			HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// todo owner check

		List<OrderDetails> resultSet = service.viewAllOrders(custService.viewCustomerById(customerId));
		if (resultSet == null) {
			return new ResponseEntity("Sorry! OrderDetails not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<OrderDetails>>(resultSet, HttpStatus.OK);
	}
}