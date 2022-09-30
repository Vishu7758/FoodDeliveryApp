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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Item;
import com.capgemini.entities.Restaurant;
import com.capgemini.service.IItemService;
import com.capgemini.service.IRestaurantService;

@RestController
public class RestaurantController {
	@Autowired
	IRestaurantService restaurantService;

	@Autowired
	IItemService itemService;

	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant saved = null;
		saved = restaurantService.addRestaurant(restaurant);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@PutMapping("/restaurant")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant newRestaurant = restaurantService.updateRestaurant(restaurant);
		if (newRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@DeleteMapping("/restaurant")
	public ResponseEntity<Restaurant> removeRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant delRestaurant = restaurantService.removeRestaurant(restaurant);
		if (delRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(delRestaurant, HttpStatus.OK);
	}

	@GetMapping("/restaurant")
	public ResponseEntity<Restaurant> viewRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant newRestaurant = restaurantService.viewRestaurant(restaurant);
		if (newRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@GetMapping("/restaurant/{nearbyName}")
	public ResponseEntity<List<Restaurant>> viewNearByRestaurant(@PathVariable String location) {
//		Restaurant newRestaurant = restaurantService.viewRestaurant(restaurant);
//		if (newRestaurant == null) {
//			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
//		}
		return null;
//		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@GetMapping("/restaurantByItemName/{itemName}")
	public ResponseEntity<List<Restaurant>> viewRestaurantByItemName(@PathVariable String name) {
//		Restaurant newRestaurant = restaurantService.viewRestaurant(restaurant);
//		if (newRestaurant == null) {
//			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
//		}
		return null;
//		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@PutMapping("/restaurant/{restaurantId}/item/{itemId}")
	public ResponseEntity<Restaurant> addItemToRestaurant(@PathVariable String restaurantId,
			@PathVariable String itemId) {
		Restaurant restaurantFromRepo = restaurantService.viewRestaurant(restaurantId);
		Item item = itemService.viewItem(itemId);
		restaurantFromRepo.addItem(item);
		restaurantFromRepo = restaurantService.updateRestaurant(restaurantFromRepo);
		return new ResponseEntity<>(restaurantFromRepo, HttpStatus.OK);

	}

//	@PostMapping("/item")
//	public ResponseEntity<Item> addItem(@RequestBody Item item) {
//		Item returnedItem = itemService.addItem(item);
//		return new ResponseEntity<>(returnedItem, HttpStatus.OK);
//	}
//
//	@GetMapping("/item/{itemId}")
//	public Item getAllItems(@PathVariable String itemId) {
//		return itemService.viewItem(itemId);
//	}

}
