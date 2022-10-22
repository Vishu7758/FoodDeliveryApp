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

import com.capgemini.entities.Item;
import com.capgemini.entities.Login;
import com.capgemini.service.ICategoryService;
import com.capgemini.service.IItemService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.IRestaurantService;

@RestController
public class ItemController {

	@Autowired
	IItemService service;
	@Autowired
	IRestaurantService resService;
	@Autowired
	ICategoryService catService;
	@Autowired
	ILoginService loginService;

	@PostMapping("/addItem")
	public ResponseEntity<Item> addItem(@RequestBody Item item, HttpServletRequest request) {
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

		Item saved = null;
		saved = service.addItem(item);
		return new ResponseEntity<Item>(saved, HttpStatus.OK);
	}

	@PutMapping("/updateItem")
	public ResponseEntity<Item> updateItem(@RequestBody Item item, HttpServletRequest request) {
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

		Item newItem = service.updateItem(item);
		if (newItem == null) {
			return new ResponseEntity("Sorry! Item not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(newItem, HttpStatus.OK);
	}

	@DeleteMapping("/removeItem")
	public ResponseEntity<Item> removeItem(@RequestBody Item item, HttpServletRequest request) {
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

		Item delItem = service.removeItem(item);
		if (delItem == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(delItem, HttpStatus.OK);
	}

	@GetMapping("/viewItem/{itemId}")
	public ResponseEntity<Item> viewItem(@PathVariable String itemId, HttpServletRequest request) {
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

		Item showItem = service.viewItem(service.viewItem(itemId));
		if (showItem == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(showItem, HttpStatus.OK);
	}

//	@GetMapping("/viewItemById/{itemId}")
//	public ResponseEntity<Item> viewItem(@PathVariable String itemId, HttpServletRequest request) {
//		// session checking
//		boolean validLogin = loginService.checkSession(request);
//		if (!validLogin) {
//			throw new IllegalArgumentException("Not logged in");
//		}
//		// todo owner check
//		Login currentUser = (Login) request.getSession().getAttribute("userDetails");
//		if (!currentUser.isOwner()) {
//			throw new IllegalArgumentException("Operation not allowed");
//		}
//
//		Item showItem = service.viewItem(itemId);
//		if (showItem == null) {
//			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Item>(showItem, HttpStatus.OK);
//	}

	@GetMapping("/viewAllItems/{categoryId}")
	public ResponseEntity<List<Item>> viewAllItemsByCategory(@PathVariable String categoryId) {
		List<Item> resultSet = service.viewAllItems(catService.viewCategoryById(categoryId));
		if (resultSet == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Item>>(resultSet, HttpStatus.OK);
	}

	@GetMapping("/viewAllItems/{restaurantId}")
	public ResponseEntity<List<Item>> viewAllItemsByRes(@PathVariable String restaurantId) {
		List<Item> resultSet = service.viewAllItems(resService.viewRestaurant(restaurantId));
		if (resultSet == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Item>>(resultSet, HttpStatus.OK);
	}

	@GetMapping("/viewAllItemsByName/{itemName}")
	public ResponseEntity<List<Item>> viewAllItemsByName(@PathVariable String itemName) {
		List<Item> resultSet = service.viewAllItemsByName(itemName);
		if (resultSet == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Item>>(resultSet, HttpStatus.OK);
	}

}