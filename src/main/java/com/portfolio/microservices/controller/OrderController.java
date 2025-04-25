package com.portfolio.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.service.OrderService;
import com.portfolio.microservices.service.UserService;
import com.portfolio.microservices.suprimeapi.api.ShopApi;
import com.portfolio.microservices.suprimeapi.model.Item;
import com.portfolio.microservices.suprimeapi.model.Order;
import com.portfolio.microservices.suprimeapi.model.User;

@RestController
@RequestMapping("/api/shop/order")
public class OrderController implements ShopApi{

    @Autowired
    private OrderService service;
    @Autowired
    private ItemService service2;
    @Autowired
    private UserService service3;

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteOrder(String orderId, @PathVariable String userId) {
        User user1 = service3.findUserById(userId);
        Order dOrder = service.findOrderById(orderId);
        Order fOrder = user1.getOrders().get(user1.getOrders().indexOf(dOrder));
        if (dOrder.equals(fOrder)) {
            service.deleteOrder(orderId, userId);
            return null;
        }
        return null;
    }

    @Override
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order response = service.findOrderById(orderId);

        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/api/shop/inventory")
    public ResponseEntity<List<Item>> getShopInventory() {
        List<Item> inventory = service2.getAllItems();

        return ResponseEntity.ok().body(inventory);
    }

    @Override
    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable String userId, Order body) {
        User foundUser = service3.findUserById(userId);

        foundUser.getOrders().addLast(body);
        service.placeOrder(body, userId);

        return ResponseEntity.ok().body(body);
    }

}
