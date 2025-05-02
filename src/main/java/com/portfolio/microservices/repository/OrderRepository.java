package com.portfolio.microservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.microservices.domain.OrderDomain;

@Repository
public interface OrderRepository extends MongoRepository<OrderDomain, String> {

}
