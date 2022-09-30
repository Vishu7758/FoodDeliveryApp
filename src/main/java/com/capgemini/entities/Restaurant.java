package com.capgemini.entities;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Restaurant {
	@Id
//	@Column(name = "restaurant_id")
	private String restaurantId;
//	@Column(name = "restaurant_name")
	private String restaurantName;
//	@OneToOne
	@Embedded
	private Address address;
	// many to many
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RESTAURANT_ITEM_TABLE", joinColumns = {
			@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId") }, inverseJoinColumns = {
					@JoinColumn(name = "item_id", referencedColumnName = "itemId") })
	private List<Item> itemList;
	private String managerName;
	private String contactNumber;

	public void addItem(Item item) {
		itemList.add(item);
	}

}
