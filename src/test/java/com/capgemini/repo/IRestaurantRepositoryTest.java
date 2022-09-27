package com.capgemini.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.Restaurant;

@SpringBootTest
class IRestaurantRepositoryTest {
	@Autowired
	IRestaurantRepository repo;

	@Test
	void readTest() {
		Restaurant restaurant = repo.findById("myID").get();
		System.out.println(restaurant);
	}

}
