package com.portfolio.microservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.microservices.domain.OrderDomain;
import com.portfolio.microservices.repository.OrderRepository;
import com.portfolio.microservices.suprimeapi.model.Order;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    private OrderDomain mapModelToDomain(Order order) {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setBillingAddress(order.getBillingAddress());
        orderDomain.setCart(order.getCart());
        orderDomain.setCartTotal(order.getCartTotal());
        orderDomain.setOrderId(order.getOrderId());
        orderDomain.setShippingAddress(order.getShippingAddress());
        orderDomain.setUser(order.getUser());

        return orderDomain;
    }

    private Order mapDomainToModel(OrderDomain domain) {
        Order order = new Order();
        order.setBillingAddress(domain.getBillingAddress());
        order.setCart(domain.getCart());
        order.setCartTotal(domain.getCartTotal());
        order.setOrderId(domain.getOrderId());
        order.setShippingAddress(domain.getShippingAddress());
        order.setUser(domain.getUser());

        return order;
    }

    public Order placeOrder(Order order) {
        return mapDomainToModel(repository.save(mapModelToDomain(order)));
    }

    public void deleteOrder(String orderId) {
        repository.deleteById(orderId);
    }

    public Order findOrderById(String orderId) {
        Optional<OrderDomain> order = repository.findById(orderId);

        Order foundOrder = mapDomainToModel(order.get());

        return foundOrder;
    }

}
