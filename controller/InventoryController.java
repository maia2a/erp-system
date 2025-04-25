package com.example.erpsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.erpsystem.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;

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
}
