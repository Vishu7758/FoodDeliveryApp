package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IRestaurantRepository;

@Service
public class IRestaurantService {
	@Autowired
	IRestaurantRepository repo;

	public Restaurant addRestaurant(Restaurant res) {
		return repo.addRestaurant(res);
	}

	public Restaurant updateRestaurant(Restaurant res) {
		return repo.updateRestaurant(res);
	}

	public Restaurant removeRestaurant(Restaurant res) {
		return repo.removeRestaurant(res);
	}

	public Restaurant viewRestaurant(Restaurant res) {
		return repo.viewRestaurant(res);
	}

	public List<Restaurant> viewNearByRestaurant(String location) {
		return repo.viewNearByRestaurant(location);
	}

	public List<Restaurant> viewRestaurantByItemName(String name) {
		return repo.viewNearByRestaurant(name);
	}
}
