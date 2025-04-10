package com.portfolio.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.service.OrderService;
import com.portfolio.microservices.suprimeapi.api.ShopApi;
import com.portfolio.microservices.suprimeapi.model.Item;
import com.portfolio.microservices.suprimeapi.model.Order;

@RestController
public class OrderController implements ShopApi {

    @Autowired
    private OrderService service;

    @Autowired
    private ItemService service2;

    @Override
    public void deleteOrder(String orderId) {
        service.deleteOrder(orderId);
    }

    @Override
    public ResponseEntity<Order> getOrderById(String orderId) {
        Order response = service.findOrderById(orderId);

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<List<Item>> getShopInventory() {
        List<Item> inventory = service2.getAllItems();

        return ResponseEntity.ok().body(inventory);
    }

    @Override
    public ResponseEntity<Order> placeOrder() {
        Order newOrder = new Order();

        return ResponseEntity.ok().body(service.placeOrder(newOrder));
    }

}
