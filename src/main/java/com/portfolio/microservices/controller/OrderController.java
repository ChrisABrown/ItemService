package com.portfolio.microservices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.suprimeapi.api.ShopApi;
import com.portfolio.microservices.suprimeapi.model.Item;
import com.portfolio.microservices.suprimeapi.model.Order;

@RestController
public class OrderController implements ShopApi {

    @Override
    public ResponseEntity<Void> deleteOrder(String orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public ResponseEntity<Order> getOrderById(String orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public ResponseEntity<Item> getShopInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShopInventory'");
    }

    @Override
    public ResponseEntity<Order> placeOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeOrder'");
    }

}
