package com.capgemini.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetails {
	@Id
	private int orderId;
	private LocalDateTime orderDate;
	@OneToOne
	@JoinColumn(name = "cartId")
	private FoodCart cart;
	private String orderStatus;
}