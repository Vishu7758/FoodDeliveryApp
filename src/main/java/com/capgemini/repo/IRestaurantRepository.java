package com.capgemini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Restaurant;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, String> {

	// using query
	@Query("select r from Restaurant r")
	public List<Restaurant> viewNearByRestaurant(String location);

	// using query
	@Query("select r from Restaurant r")
	public List<Restaurant> viewRestaurantByItemName(String name);
}
