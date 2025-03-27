package com.portfolio.microservices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.suprimeapi.api.ItemApi;
import com.portfolio.microservices.suprimeapi.model.Item;

@RestController
public class ItemController implements ItemApi {

    @Override
    public ResponseEntity<Item> createItem(Item body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createItem'");
    }

    @Override
    public ResponseEntity<Item> deleteItem(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteItem'");
    }

    @Override
    public ResponseEntity<Item> getAllItems(Item body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllItems'");
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
