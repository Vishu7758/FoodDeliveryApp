package com.capgemini.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
	@Id
	@Column(name = "restaurant_id")
	private String restaurantId;
	@Column(name = "restaurant_name")
	private String restaurantName;
//	@OneToOne
	@Embedded
	private Address address;
	// many to many
	@ManyToMany
	private List<Item> itemList;
	private String managerName;
	private String contactNumber;

}
