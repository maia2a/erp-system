package com.example.erpsystem.service;

import com.example.erpsystem.entity.InvetoryItem;
import com.example.erpsystem.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
  private final InventoryRepository repository;

  public InventoryService(InventoryRepository repository) {
    this.repository = repository;
  }

  public List<InvetoryItem> getAllItems() {
    return repository.findAll();
  }
}
