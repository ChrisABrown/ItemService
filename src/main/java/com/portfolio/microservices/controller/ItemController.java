package com.portfolio.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.suprimeapi.api.ItemApi;
import com.portfolio.microservices.suprimeapi.model.Item;

@RestController
@RequestMapping("/api/item")
public class ItemController implements ItemApi {
    @Autowired
    private ItemService service;

    @Override
    @PostMapping("/")
    public ResponseEntity<Item> createItem(Item body) {
        Item response = service.createItem(body);

        return ResponseEntity.ok().body(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        service.deleteItem(id);
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable String id) {
        Item gItem = service.getItem(id);

        return ResponseEntity.ok().body(gItem);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, Item body) {
        Item uItem = service.updateItem(body);

        return ResponseEntity.ok().body(uItem);
    }

}