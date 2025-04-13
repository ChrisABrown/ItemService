// package com.portfolio.microservices.service;

// import java.util.LinkedList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.portfolio.microservices.domain.ItemDomain;
// import com.portfolio.microservices.repository.ItemRepository;
// import com.portfolio.microservices.suprimeapi.model.Item;

// @Service
// public class ItemService {

// @Autowired
// private ItemRepository repository;

// private ItemDomain mapModelToDomain(Item item) {
// ItemDomain itemDomain = new ItemDomain();
// itemDomain.setItemId(item.getItemId());
// itemDomain.setCategory(item.getCategory());
// itemDomain.setDescription(item.getDescription());
// itemDomain.setImage(item.getImage());
// itemDomain.setItemName(item.getItemName());
// itemDomain.setPrice(item.getPrice());
// itemDomain.setSKU(item.getSKU());
// itemDomain.setSize(item.getSize());

// return itemDomain;
// }

// private Item mapDomainToModel(ItemDomain domain) {
// Item item = new Item();
// item.setItemId(domain.getItemId());
// item.setItemId(domain.getItemId());
// item.setCategory(domain.getCategory());
// item.setDescription(domain.getDescription());
// item.setImage(domain.getImage());
// item.setItemName(domain.getItemName());
// item.setPrice(domain.getPrice());
// item.setSKU(domain.getSKU());
// item.setSize(domain.getSize());

// return item;
// }

// public Item createItem(Item body) {
// return mapDomainToModel(repository.save(mapModelToDomain(body)));
// }

// public void deleteItem(String id) {
// repository.deleteById(id);
// }

// public List<Item> getAllItems() {
// List<ItemDomain> items = repository.findAll();
// List<Item> changedItems = new LinkedList<>();

// for (ItemDomain itemDomain : items) {
// int x = 0;
// Item item = mapDomainToModel(itemDomain);
// changedItems.set(x, item);
// x++;
// return changedItems;
// }
// return changedItems;
// }

// public Item getItem(String id) {
// return mapDomainToModel(repository.findByItemId(id));
// }

// public Item updateItem(Item body) {
// return mapDomainToModel(repository.insert(mapModelToDomain(body)));
// }

// }
