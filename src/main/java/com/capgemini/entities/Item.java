package com.capgemini.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Item {
	@Id
	private String itemId;
	private String itemName;
//	@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@JoinColumn(name = "catId")
	private Category category;
	private int quantity;
	private double cost;
	// Owner of this is Restaurant class
	@ManyToMany(mappedBy = "itemList", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Restaurant> restaurants;

}
