package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IRestaurantRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
	@Autowired
	IRestaurantRepository repo;

	@Override
	public Restaurant addRestaurant(Restaurant res) {
		return repo.save(res);
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res) {
		Restaurant updated = null;
		if (repo.existsById(res.getRestaurantId()))
			updated = repo.save(res);

		return updated;
	}

	@Override
	public Restaurant removeRestaurant(Restaurant res) {
		Restaurant deleted = res;
		if (repo.existsById(res.getRestaurantId())) {
			repo.delete(res);
			return deleted;
		}

		return null;
	}

	@Override
	public Restaurant viewRestaurant(Restaurant res) {
		return repo.findById(res.getRestaurantId()).get();
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) {
		return repo.viewNearByRestaurant(location);
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String name) {
		return repo.viewRestaurantByItemName(name);
	}

	@Override
	public Restaurant viewRestaurant(String restaurantId) {
		return repo.findById(restaurantId).get();
	}

}
