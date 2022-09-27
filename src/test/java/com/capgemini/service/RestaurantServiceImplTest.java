package com.capgemini.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IRestaurantRepository;

@SpringBootTest
class RestaurantServiceImplTest {

	@Autowired
	IRestaurantRepository repository;

	@Autowired
	IRestaurantService service;

	@Test
	@Order(1)
	@DisplayName("Adding Restaurant")
	void createTest() {
	}

	@Test
	@DisplayName("Update Restaurant")
	void updateTest() {
		Restaurant restaurant = service.Restaurant.setUrl("New URL");
		repository.save(Restaurant);
		System.err.println("Updated new url to Restaurant");

	}

	@Test
	@Order(2)
	@DisplayName("Read Restaurant by id")
	void readTest() {
		CourseRestaurant Restaurant = service.read(1L).get();
		System.err.println(Restaurant);

	}

	@Test
	@Order(5)
	@DisplayName("Delete Restaurant by id")
	void deleteByIdTest() {
//		service.delete(1L);
		System.err.println("Deleted with ID 1");
	}

	@Test
	@Order(6)
	@DisplayName("Delete by obj Restaurant")
	void deleteByIdObj() {
		CourseRestaurant Restaurant = new CourseRestaurant();
		Restaurant.setRestaurantId(3L);
		service.delete(Restaurant);
		System.err.println("Obj deleted");

	}

	@Test
	@Order(4)
	@DisplayName("Find all Restaurant")
	void findAllTest() {
		List<CourseRestaurant> Restaurants = service.findAll();
		System.err.println(Restaurants);
	}

}
