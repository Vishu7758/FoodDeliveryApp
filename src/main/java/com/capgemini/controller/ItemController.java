package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.entities.Item;
import com.capgemini.service.IItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	IItemService service;

	@PostMapping
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		Item saved = null;
		saved = service.addItem(item);
		return new ResponseEntity<Item>(saved, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Item> updateItem(@RequestBody Item item) {
		Item newItem = service.updateItem(item);
		if (newItem == null) {
			return new ResponseEntity("Sorry! Item not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(newItem, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Item> removeItem(@RequestBody Item item) {
		Item delItem = service.removeItem(item);
		if (delItem == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(delItem, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Item> viewItem(@RequestBody Item item) {
		Item showItem = service.viewItem(item);
		if (showItem == null) {
			return new ResponseEntity("Sorry! Item not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(showItem, HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public Item getItemById(@PathVariable String itemId) {
		return service.viewItem(itemId);
	}
}
