package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.entities.Item;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IItemRepository;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	IItemRepository itemRepo;

	@Override
	public Item addItem(Item item) {
		return itemRepo.save(item);
	}

	@Override
	public Item updateItem(Item item) {
		Item itemUpdate = null;
		if (itemRepo.existsById(item.getItemId()))
			itemUpdate = itemRepo.save(item);
		return itemUpdate;
	}

	@Override
	public Item viewItem(Item item) {
		return itemRepo.findById(item.getItemId()).get();
	}

	@Override
	public Item viewItem(String itemId) {
		return itemRepo.findById(itemId).get();
	}

	@Override
	public Item removeItem(Item item) {
		Item itemUpdate = item;
		if (itemRepo.existsById(item.getItemId())) {
			itemRepo.delete(item);
			return itemUpdate;
		}
		return null;
	}

	@Override
	public List<Item> viewAllItems(Category cat) {
		return itemRepo.viewAllItems(cat.getCatId(), cat.getCategoryName());
	}

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		return itemRepo.viewAllItems(res.getRestaurantId());
	}

	@Override
	public List<Item> viewAllItemsByName(String name) {
		return itemRepo.viewAllItemsByName(name);
	}

}
