package com.portfolio.microservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.microservices.domain.ItemDomain;

@Repository
public interface ItemRepository extends MongoRepository<ItemDomain, String> {

}