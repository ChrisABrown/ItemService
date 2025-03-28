package com.portfolio.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Item> deleteItem(String id) {
        Item response = service.deleteItem(id);

    }

    @Override
    public ResponseEntity<Item> getAllItems(Item body) {
        Item response = service.getAllItems(body)
    }

    @Override
    public ResponseEntity<Item> getItem(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItem'");
    }

    @Override
    public ResponseEntity<Item> updateItem(Item body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItem'");
    }

}
