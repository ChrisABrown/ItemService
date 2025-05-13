package com.portfolio.itemservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.itemservice.domain.ItemDomain;

@Repository
public interface ItemRepository extends MongoRepository<ItemDomain, String> {

    // Custom query to find an item by its ID
    @Query("{'itemId': ?0}")
    ItemDomain findByItemId(UUID itemId);

    // Custom query to find an item by its SKU
    @Query("{'sku': ?0}")
    ItemDomain findBySKU(String sku);

    // Custom query to find an item by its category
    @Query("{'category': ?0}")
    List<ItemDomain> findByCategory(String category);

    // Custom query to find items by their price range
    @Query("{'price': {$gte: ?0, $lte: ?1}}")
    List<ItemDomain> findByPriceRange(double minPrice, double maxPrice);

    // Custom query to find items with stock greater than a certain value
    @Query("{'stock': {$gt: ?0}}")
    List<ItemDomain> findByStockGreaterThan(int stock);

    // Custom query to find items by their name
    @Query("{'itemName': ?0}")
    List<ItemDomain> findByName(String itemName);
}
