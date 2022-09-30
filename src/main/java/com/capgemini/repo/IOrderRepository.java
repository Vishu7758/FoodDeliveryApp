package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails, Integer> {

//	@Query("select o from order o")
//	public List<OrderDetails> viewAllOrders(Restaurant res);
//
//	@Query("select o from order o")
//	public List<OrderDetails> viewAllOrders(Customer customer);

}