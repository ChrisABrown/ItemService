package com.portfolio.microservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.microservices.domain.OrderDomain;

public interface OrderRepository extends MongoRepository<OrderDomain, String> {

}
