package com.example.erpsystem.service;

import com.example.erpsystem.entity.InvetoryItem;
import com.example.erpsystem.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
  private final InventoryRepository repository;

  public InventoryService(InventoryRepository repository) {
    this.repository = repository;
  }

  // This method retrieves all items from the inventory and returns them as a list
  public List<InvetoryItem> getAllItems() {
    return repository.findAll();
  }

  // This method retrieves an item by its ID and returns it wrapped in an Optional
  public Optional<InvetoryItem> getItemById(Long id) {
    return repository.findById(id);
  }

  // This method creates a new item and returns the created item
  public InvetoryItem createItem(InvetoryItem item) {
    return repository.save(item);
  }

  // This method updates an existing item by its ID and returns the updated item
  public Optional<InvetoryItem> updateItem(Long id, InvetoryItem item) {
    return repository.findById(id).map(existingItem -> {
      existingItem.setName(item.getName());
      existingItem.setQuantity(item.getQuantity());
      existingItem.setPrice(item.getUnitPrice());
      return repository.save(existingItem);
    });
  }

  // This method deletes an item by its ID and returns true if the item was
  // deleted, false otherwise
  public boolean deleteItem(Long id) {
    return repository.findById(id).map(item -> {
      repository.delete(item);
      return true;
    }).orElse(false);
  }
}
