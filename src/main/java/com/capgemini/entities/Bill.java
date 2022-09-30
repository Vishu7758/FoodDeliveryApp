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
public class Bill {
	@Id
	private String billId;

//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime billDate;

	@OneToOne
	@JoinColumn(name = "orderId")
	private OrderDetails order;
	private int totalItem;
	private double totalCost;

}