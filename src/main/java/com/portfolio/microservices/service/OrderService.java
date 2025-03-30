package com.portfolio.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.microservices.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

}
