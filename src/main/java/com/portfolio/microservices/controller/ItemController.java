package com.portfolio.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.suprimeapi.api.ItemApi;
import com.portfolio.microservices.suprimeapi.model.Item;

@RestController
public class ItemController implements ItemApi {
    @Autowired
    private ItemService service;

    @Override
    public ResponseEntity<Item> createItem(Item body) {
        Item response = service.createItem(body);

        return ResponseEntity.ok().body(response);
    }

    @Override
    public void deleteItem(String id) {
        service.deleteItem(id);
    }

    @Override
    public ResponseEntity<List<Item>> getAllItems(List<Item> items) {
        items = service.getAllItems();

        return ResponseEntity.ok().body(items);
    }

    @Override
    public ResponseEntity<Item> getItem(String id) {
        Item gItem = service.getItem(id);

        return ResponseEntity.ok().body(gItem);
    }

    @Override
    public ResponseEntity<Item> updateItem(Item body) {
        Item uItem = service.updateItem(body);

        return ResponseEntity.ok().body(uItem);
    }

}