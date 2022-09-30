package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, String> {

//	public List<Item> viewAllItems(Category cat);
//	public List<Item> viewAllItems(Restaurant res);
//	@Query(value="Select * from item i where i.itemName=?1",nativeQuery = true)
//	public List<Item> viewAllItemsByName(String name);
}