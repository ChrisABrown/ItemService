package com.portfolio.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.microservices.repository.ItemRepository;
import com.portfolio.microservices.suprimeapi.model.Item;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Item createItem(Item body) {
        return repository.saveItem(body);
    }

    public Item deleteItem(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteItem'");
    }

    public Item getAllItems(Item body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllItems'");
    }

    public Item getItem(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItem'");
    }

    public Item updateItem(Item body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItem'");
    }

}
