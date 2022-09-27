package com.capgemini.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	String item_id;
}
