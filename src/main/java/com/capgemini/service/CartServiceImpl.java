package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.FoodCart;
import com.capgemini.entities.Item;
import com.capgemini.repo.ICartRepository;

@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	ICartRepository cartRepo;

	public FoodCart addItemToCart(FoodCart cart, Item item) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		tempItems.add(item);
		repoCart.setItemList(tempItems);
		return repoCart;
	}

	public FoodCart IncreaseQuantity(FoodCart cart, Item item, int quantity) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		for(Item it:tempItems) {
			if(it.getItemId().equals(item.getItemId())) {
				int quant = it.getQuantity();
				it.setQuantity(quant+quantity);
				break;
			}
		}
		
		repoCart.setItemList(tempItems);
		return repoCart;
	}

	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		for(Item it:tempItems) {
			if(it.getItemId().equals(item.getItemId())) {
				int quant = it.getQuantity();
				it.setQuantity(quant-quantity);
				break;
			}
		}
		repoCart.setItemList(tempItems);
		return repoCart;
	}

	public FoodCart removeItem(FoodCart cart, Item item) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		tempItems.remove(item);
		repoCart.setItemList(tempItems);
		return repoCart;
	}

	public FoodCart clearCart(FoodCart cart) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		tempItems.clear();
		return repoCart;
	}

	
}
