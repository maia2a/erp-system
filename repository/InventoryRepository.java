package com.example.erpsystem.repository;

import com.example.erpsystem.entity.InvetoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InvetoryItem, Long> {
  // This interface extends JpaRepository to provide CRUD operations for
  // InvetoryItem entities.
  // You can add custom query methods here if needed.
}
