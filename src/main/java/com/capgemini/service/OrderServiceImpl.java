package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.OrderDetails;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepo;

	@Override
	public OrderDetails addOrder(OrderDetails order) {
		return orderRepo.save(order);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		OrderDetails orderUpdate = null;
		if (orderRepo.existsById(order.getOrderId()))
			orderUpdate = orderRepo.save(order);
		return orderUpdate;
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		OrderDetails orderUpdate = order;
		if (orderRepo.existsById(order.getOrderId())) {
			orderRepo.delete(order);
			return orderUpdate;
		}
		return null;
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) {
		return orderRepo.findById(order.getOrderId()).get();
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant res) {
		return orderRepo.viewAllOrders(res.getRestaurantId(), res.getRestaurantName());
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		return orderRepo.viewAllOrders(customer.getCustomerId());
	}

}