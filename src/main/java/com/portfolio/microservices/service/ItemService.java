package com.portfolio.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.microservices.domain.ItemDomain;
import com.portfolio.microservices.repository.ItemRepository;
import com.portfolio.microservices.suprimeapi.model.Item;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    private ItemDomain mapModelToDomain(Item item) {
        ItemDomain itemDomain = new ItemDomain();
        itemDomain.setItemId(itemDomain.getItemId());

        return itemDomain;
    }

    private Item mapDomainToModel(ItemDomain domain) {
        Item item = new Item();
        item.setItemId(item.getItemId());

        return item;
    }

    public Item createItem(Item body) {
        return mapDomainToModel(repository.save(mapModelToDomain(body)));
    }

    public Item deleteItem(String id) {
        return repository.deleteItem(id);
    }

    public Item getAllItems(Item body) {
        return repository.getAllItems(body);
    }

    public Item getItem(String id) {
        return repository.getItem(id);
    }

    public Item updateItem(Item body) {
        return repository.updateItem(body);
    }

}
