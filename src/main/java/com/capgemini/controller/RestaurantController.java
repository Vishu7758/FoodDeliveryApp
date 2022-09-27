package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Restaurant;
import com.capgemini.service.IRestaurantService;

@RestController
public class RestaurantController {
	@Autowired
	IRestaurantService service;

	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant saved = null;
		saved = service.addRestaurant(restaurant);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@PutMapping("/restaurant")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant newRestaurant = service.updateRestaurant(restaurant);
		if (newRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@DeleteMapping("/restaurant")
	public ResponseEntity<Restaurant> removeRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant delRestaurant = service.removeRestaurant(restaurant);
		if (delRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(delRestaurant, HttpStatus.OK);
	}

	@GetMapping("/restaurant")
	public String test() {
		return "Heyy";
	}

	@GetMapping("/getAllRestaurants")
	public String getAll() {
		return "Heyy";
	}
}
