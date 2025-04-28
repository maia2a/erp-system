package com.example.erpsystem.controller;

import com.example.erpsystem.entity.InventoryItem;
import com.example.erpsystem.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
  private final InventoryService service;

  public InventoryController(InventoryService service) {
    this.service = service;
  }

  @GetMapping
  public List<InventoryItem> getAllItems() {
    return service.getAllItems();
  }

  @GetMapping("/{id}")
  public ResponseEntity<InventoryItem> getItemById(@PathVariable Long id) {
    return service.getItemById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<InventoryItem> createItem(@RequestBody InventoryItem item) {
    InventoryItem savedItem = service.createItem(item);
    return ResponseEntity.status(201).body(savedItem);
  }

  @PutMapping("/{id}")
  public ResponseEntity<InventoryItem> updateItem(@PathVariable Long id, @RequestBody InventoryItem updatedItem) {
    return service.updateItem(id, updatedItem)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    if (service.deleteItem(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}