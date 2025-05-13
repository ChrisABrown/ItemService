package com.portfolio.itemservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.itemservice.service.ItemService;
import com.portfolio.itemservice.suprimeapi.api.ItemApi;
import com.portfolio.itemservice.suprimeapi.model.Item;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemController implements ItemApi {
    // This class implements the ItemApi interface and provides the implementation for the methods defined in the interface.
    // It uses the ItemService to perform the actual operations.
    // The @RestController annotation indicates that this class is a REST controller, and the @RequestMapping annotation specifies the base URL for all the endpoints in this controller.
    // The @Autowired annotation is used to inject the ItemService bean into this controller.
    // The ItemApi interface defines the contract for the ItemController, and the ItemService is responsible for the business logic related to items.
    // The ItemController class is responsible for handling HTTP requests related to items.

    @Autowired
    private ItemService service;

    @Override
    @PostMapping("/")
    public ResponseEntity<Item> createItem(@RequestBody Item body) {
        Item response = service.createItem(body);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        service.deleteItem(id);
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable UUID id) {
        Item gItem = service.getItem(id);
        return ResponseEntity.ok().body(gItem);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable UUID id, @Valid Item body) {
        Item uItem = service.updateItem(id, body);
        return ResponseEntity.ok().body(uItem);
    }
}
