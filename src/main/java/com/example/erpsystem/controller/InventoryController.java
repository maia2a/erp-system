package com.example.erpsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.erpsystem.service.InventoryService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/inventory")

public class InventoryController {
  private final InventoryService service;

  public InventoryController(InventoryService service) {
    this.service = service;
  }

  // Get all items
  @GetMapping
  public List<InventoryItem> getAllItems() {
    return service.getAllItems();
  }

  // Get an item by ID
  @GetMapping("/{id}")
  public ResponseEntity<InventoryItem> getItemById(@PathVariable Long id) {
    return service.getItemById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Create a new item
  @PostMapping
  public ResponseEntity<InventoryItem> createItem(@RequestBody InventoryItem item) {
    InventoryItem savedItem = service.createItem(item);
    return ResponseEntity.status(201).body(savedItem);
  }

  // Update an item by ID
  @PutMapping("/{id}")
  public ResponseEntity<InventoryItem> updateItem(@PathVariable Long id, @RequestBody InventoryItem item) {
    return service.updateItem(id, updatedItem)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Delete an item by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    if (service.deleteItem(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
