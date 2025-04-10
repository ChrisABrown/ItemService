package com.portfolio.microservices.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.microservices.domain.ItemDomain;

@Repository
public interface ItemRepository extends MongoRepository<ItemDomain, String> {

    @Query("{'itemId': ?0}")
    ItemDomain findByItemId(String itemId);

    @Query
    List<ItemDomain> findAll();

}