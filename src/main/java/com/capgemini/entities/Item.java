package com.capgemini.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
	@Id
	private String itemId;
	private String itemName;
//	private Category category;
	private int quantity;
	private double cost;

	@ManyToMany(mappedBy = "itemList")
	@JsonBackReference
	private List<Restaurant> restaurants;

}
