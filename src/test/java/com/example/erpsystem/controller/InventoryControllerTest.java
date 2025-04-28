package com.example.erpsystem.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.erpsystem.entity.InventoryItem; // Import the InventoryItem entity
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc

class InventoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCreateAndGetItem() throws Exception {
    InventoryItem newItem = new InventoryItem(null, "Test Item", 10, 100.0);

    // Create a new item
    mockMvc.perform(post("/api/inventory")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(newItem)))
        .andExpect(status().isCreated());

    // Get all items
    MvcResult result = mockMvc.perform(get("/api/inventory"))
        .andExpect(status().isOk())
        .andReturn();

    // Convert the response to a list of InventoryItem
    // and check if the item is present
    List<InventoryItem> items = new ObjectMapper()
        .readValue(result.getResponse().getContentAsString(), new TypeReference<List<InventoryItem>>() {
        });
    assertNotNull(items);
    assertFalse(items.isEmpty());
    assertEquals("Test Item", items.get(0).getName());
  }
}
