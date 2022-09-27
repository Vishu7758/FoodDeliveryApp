package com.capgemini;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.repo.IRestaurantRepository;
import com.capgemini.service.IRestaurantService;
import com.capgemini.service.RestaurantServiceImpl;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class FoodDeliveryAppApplicationTest {

	private IRestaurantService service;

	@Mock
	private IRestaurantRepository repository;

	@BeforeEach
	void setUp() {
		this.service = new RestaurantServiceImpl(this.repository);
	}

	@Test
	public void viewNearByRestaurantTest() {
		service.viewNearByRestaurant("Vishal");
		verify(repository).viewNearByRestaurant("Vishal");
	}
}
