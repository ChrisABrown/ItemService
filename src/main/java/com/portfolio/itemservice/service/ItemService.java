package com.portfolio.itemservice.service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.portfolio.itemservice.domain.ItemDomain;
import com.portfolio.itemservice.repository.ItemRepository;
import com.portfolio.itemservice.suprimeapi.model.Item;

@Service
@Lazy
public class ItemService {

    @Autowired
    private ItemRepository repo;
    private final String ITEM_NOT_FOUND = "Item not found with id: ";
    private final String MODEL_ERROR_MESSAGE = "Error in in mapping model to domain: ";
    private final String DOMAIN_ERROR_MESSAGE = "Error in in mapping domain to model: ";
    private final String ITEM_ERROR_MESSAGE = "Error in getting all items: ";
    private final String ITEM_ID = "Item ID cannot be null";
    private final String CATEGORY = "Category cannot be null";
    private final String DESCRIPTION = "Description cannot be null";
    private final String IMAGE = "Image cannot be null";
    private final String ITEM_NAME = "Item Name cannot be null";
    private final String PRICE = "Price cannot be null";
    private final String SKU = "SKU cannot be null";
    private final String SIZE = "Size cannot be null";

    // Constructor-based dependency injection for ItemRepository
    public ItemService(ItemRepository itemRepository) {
        this.repo = itemRepository;
    }

    // This method maps the Item model to ItemDomain
    @SuppressWarnings("unused")
    private ItemDomain mapModelToDomain(Item item) {
        ItemDomain itemDomain = new ItemDomain();
        // Set the properties of itemDomain from item
        // Use try-catch to handle any potential exceptions during mapping
        try {
            itemDomain.setItemDomainId(item.getItemId());
            itemDomain.setCategory(item.getCategory());
            itemDomain.setDescription(item.getDescription());
            itemDomain.setImage(item.getImage());
            itemDomain.setItemName(item.getItemName());
            itemDomain.setPrice(item.getPrice());
            itemDomain.setSKU(item.getSKU());
            itemDomain.setSize(item.getSize());
        } catch (RuntimeException e) {
            if (item.getItemId() == null) {
                throw new RuntimeException(ITEM_ID);
            }
            if (item.getCategory() == null) {
                throw new RuntimeException(CATEGORY);
            }
            if (item.getDescription() == null) {
                throw new RuntimeException(DESCRIPTION);
            }
            if (item.getImage() == null) {
                throw new RuntimeException(IMAGE);
            }
            if (item.getItemName() == null) {
                throw new RuntimeException(ITEM_NAME);
            }
            if (item.getPrice() == null) {
                throw new RuntimeException(PRICE);
            }
            if (item.getSKU() == null) {
                throw new RuntimeException(SKU);
            }
            if (item.getSize() == null) {
                throw new RuntimeException(SIZE);
            }
            System.out.println(MODEL_ERROR_MESSAGE + e.getMessage());
        }
        return itemDomain;
    }

    // This method maps the ItemDomain to Item model
    private Item mapDomainToModel(ItemDomain domain) {
        Item item = new Item();
        // Set the properties of item from domain
        // Use try-catch to handle any potential exceptions during mapping
        try {
            item.setItemId(domain.getItemDomainId());
            item.setCategory(domain.getCategory());
            item.setDescription(domain.getDescription());
            item.setImage(domain.getImage());
            item.setItemName(domain.getItemName());
            item.setPrice(domain.getPrice());
            item.setSKU(domain.getSKU());
            item.setSize(domain.getSize());
            return item;
        } catch (RuntimeException e) {
            if (domain == null) {
                throw new RuntimeException("ItemDomain cannot be null");
            }
            if (domain.getItemDomainId() == null) {
                throw new RuntimeException(ITEM_ID);
            }
            if (domain.getCategory() == null) {
                throw new RuntimeException(CATEGORY);
            }
            if (domain.getDescription() == null) {
                throw new RuntimeException(DESCRIPTION);
            }
            if (domain.getImage() == null) {
                throw new RuntimeException(IMAGE);
            }
            if (domain.getItemName() == null) {
                throw new RuntimeException(ITEM_NAME);
            }
            if (domain.getPrice() == null) {
                throw new RuntimeException(PRICE);
            }
            if (domain.getSKU() == null) {
                throw new RuntimeException(SKU);
            }
            if (domain.getSize() == null) {
                throw new RuntimeException(SIZE);
            }
            System.out.println(DOMAIN_ERROR_MESSAGE + e.getMessage());
        }
        // If the item is found, return the populated item
        return item;
    }

    public Item createItem(Item body) {
        // Check if the item already exists
        ItemDomain existingItem = repo.findByItemId(body.getItemId());
        if (existingItem != null) {
            // If the item already exists, return the existing item
            return mapDomainToModel(existingItem);
        }
        // If the item does not exist, create a new one
        // Use try-catch to handle any potential exceptions during saving
        // and to ensure that the method returns null if an error occurs
        ItemDomain itemDomain = new ItemDomain();
        try {
            itemDomain.setItemDomainId(body.getItemId());
            itemDomain.setCategory(body.getCategory());
            itemDomain.setDescription(body.getDescription());
            itemDomain.setImage(body.getImage());
            itemDomain.setItemName(body.getItemName());
            itemDomain.setPrice(body.getPrice());
            itemDomain.setSKU(body.getSKU());
            itemDomain.setSize(body.getSize());
            ItemDomain savedItem = itemDomain;
            // Save the new item to the repository
            repo.insert(savedItem);
            // Print the item ID for debugging purposes
            // This line is for debugging purposes
            // It can be removed or replaced with a logger in production code
            System.out.println("Item ID: " + savedItem.getItemDomainId());
            // Save the new item to the repository    
            return mapDomainToModel(savedItem);
        } catch (RuntimeException e) {
            System.out.println(MODEL_ERROR_MESSAGE + e.getMessage());
            // If an error occurs, return null
            return null;
        }
    }

    public void deleteItem(UUID id) {
        // Check if the item exists before attempting to delete
        ItemDomain existingItem = repo.findByItemId(id);
        if (existingItem == null) {
            // If the item does not exist, throw an exception or handle it accordingly
            throw new RuntimeException(ITEM_NOT_FOUND + id);
        }
        // If the item exists, delete it
        // Use try-catch to handle any potential exceptions during deletion
        try {
            repo.delete(existingItem);
        } catch (Exception e) {
            System.out.println(ITEM_ERROR_MESSAGE + e.getMessage());
        }
        // Alternatively, the deleteById method can be used directly
        //
        // Uncomment the line below if you want to use deleteById instead
        // repo.deleteById(id);
    }

    public List<Item> getAllItems() {
        List<ItemDomain> items = repo.findAll();
        List<Item> changedItems = new LinkedList<>();
        // Use try-catch to handle any potential exceptions during mapping
        // and to ensure that the method returns an empty list if an error occurs
        try {
            for (ItemDomain itemDomain : items) {
                Item item = mapDomainToModel(itemDomain);
                changedItems.add(item);
            }
        } catch (Exception e) {
            System.out.println(ITEM_ERROR_MESSAGE + e.getMessage());
        }
        // If the list is empty, return an empty list
        if (changedItems.isEmpty()) {
            return new LinkedList<>();
        }
        // If the list is not empty, return the populated list
        return changedItems;
    }

    public Item getItem(UUID id) {
        // Check if the item exists before attempting to retrieve it
        ItemDomain existingItem = repo.findByItemId(id);
        if (existingItem == null) {
            // If the item does not exist, throw an exception or handle it accordingly
            throw new RuntimeException(ITEM_NOT_FOUND + id);
        }
        // If the item exists, retrieve it
        // Use try-catch to handle any potential exceptions during retrieval
        // and to ensure that the method returns null if an error occurs
        try {
            return mapDomainToModel(existingItem);
        } catch (Exception e) {
            System.out.println(ITEM_ERROR_MESSAGE + e.getMessage());
        }
        // If the item is not found, return null
        return null;
    }

    public Item updateItem(UUID id, Item body) {
        // Check if the item exists before attempting to update it
        ItemDomain existingItem = repo.findByItemId(id);
        // If the item exists, update it
        // Use try-catch to handle any potential exceptions during updating
        // and to ensure that the method returns null if an error occurs
        try {
            if (existingItem == null) {
                // If the item does not exist, throw an exception or handle it accordingly
                throw new RuntimeException(ITEM_NOT_FOUND + id);
            }
            existingItem.setItemDomainId(id);
            existingItem.setCategory(body.getCategory());
            existingItem.setDescription(body.getDescription());
            existingItem.setImage(body.getImage());
            existingItem.setItemName(body.getItemName());
            existingItem.setPrice(body.getPrice());
            existingItem.setSKU(body.getSKU());
            existingItem.setSize(body.getSize());

            return mapDomainToModel(repo.insert(existingItem));
        } catch (RuntimeException e) {
            System.out.println(ITEM_ERROR_MESSAGE + e.getMessage());
        }
        // If an error occurs, return null
        return null;
    }
}
