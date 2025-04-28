package com.example.erpsystem.service;

import com.example.erpsystem.entity.InventoryItem;
import com.example.erpsystem.repository.InventoryRepository; // Ensure this import is correct
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
  private final InventoryRepository repository;

  public InventoryService(InventoryRepository repository) {
    this.repository = repository;
  }

  public List<InventoryItem> getAllItems() {
    return repository.findAll();
  }

  public Optional<InventoryItem> getItemById(Long id) {
    return repository.findById(id);
  }

  public InventoryItem createItem(InventoryItem item) {
    return repository.save(item);
  }

  public Optional<InventoryItem> updateItem(Long id, InventoryItem updatedItem) {
    return repository.findById(id).map(existingItem -> {
      existingItem.setName(updatedItem.getName());
      existingItem.setQuantity(updatedItem.getQuantity());
      existingItem.setUnitPrice(updatedItem.getUnitPrice()); // Corrected from setPrice to setUnitPrice
      return repository.save(existingItem);
    });
  }

  public boolean deleteItem(Long id) {
    return repository.findById(id).map(item -> {
      repository.delete(item);
      return true;
    }).orElse(false);
  }
}