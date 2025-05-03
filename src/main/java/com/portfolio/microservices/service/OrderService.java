package com.portfolio.microservices.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.microservices.domain.OrderDomain;
import com.portfolio.microservices.domain.UserDomain;
import com.portfolio.microservices.repository.OrderRepository;
import com.portfolio.microservices.repository.UserRepository;
import com.portfolio.microservices.suprimeapi.model.Order;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final UserRepository userRepo;
    private static final String ORDER_NOT_FOUND = "Order not found";
    private static final String USER_NOT_FOUND = "User not found";
    private static final String ORDER_ID_REQUIRED = "Order ID is required";
    private static final String USER_ID_REQUIRED = "User ID is required";
    private static final String CART_REQUIRED = "Cart is required";
    private static final String CART_GREATER_THAN_ZERO = "Cart total must be greater than zero";
    private static final String SHIPPING_IS_REQUIRED = "Shipping address is required";
    private static final String BILLING_IS_REQUIRED = "Billing address is required";
    private static final String DOMAIN_ERROR = "Error in mapping model to domain: ";
    private static final String MODEL_ERROR = "Error in mapping domain to model: ";
    private static final String ORDER_ERROR = "Error in placing order: ";

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.userRepo = userRepository;
        this.repo = orderRepository;
    }

    private OrderDomain mapModelToDomain(Order order) {
        OrderDomain orderDomain = new OrderDomain();
        try {
            orderDomain.setBillingAddress(order.getBillingAddress());
            orderDomain.setOrderId(order.getOrderId());
            orderDomain.setCart(order.getCart());
            orderDomain.setCartTotal(order.getCartTotal());
            orderDomain.setShippingAddress(order.getShippingAddress());
            orderDomain.setUser(order.getUser());
        } catch (RuntimeException e) {
            if (order == null) {
                throw new RuntimeException(ORDER_NOT_FOUND);
            }
            if (order.getUser() == null || order.getUser().isEmpty()) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
            if (order.getCart() == null || order.getCart().isEmpty()) {
                throw new RuntimeException(CART_REQUIRED);
            }
            if (order.getCartTotal() <= 0) {
                throw new RuntimeException(CART_GREATER_THAN_ZERO);
            }
            if (order.getShippingAddress() == null || order.getShippingAddress().isEmpty()) {
                throw new RuntimeException(SHIPPING_IS_REQUIRED);
            }
            if (order.getBillingAddress() == null || order.getBillingAddress().isEmpty()) {
                throw new RuntimeException(BILLING_IS_REQUIRED);
            }
            System.out.println(DOMAIN_ERROR + e.getMessage());
        }
        return orderDomain;
    }

    private Order mapDomainToModel(OrderDomain domain) {
        Order order = new Order();
        try {
            order.setBillingAddress(domain.getBillingAddress());
            order.setCart(domain.getCart());
            order.setCartTotal(domain.getCartTotal());
            order.setOrderId(domain.getOrderId());
            order.setShippingAddress(domain.getShippingAddress());
            order.setUser(domain.getUser());
        } catch (RuntimeException e) {
            if (domain == null) {
                throw new RuntimeException(ORDER_NOT_FOUND);
            }
            if (domain.getUser() == null || domain.getUser().isEmpty()) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
            if (domain.getOrderId() == null || domain.getOrderId().isEmpty()) {
                throw new RuntimeException(ORDER_ID_REQUIRED);
            }
            if (domain.getCart() == null || domain.getCart().isEmpty()) {
                throw new RuntimeException(CART_REQUIRED);
            }
            if (domain.getCartTotal() <= 0) {
                throw new RuntimeException(CART_GREATER_THAN_ZERO);
            }
            if (domain.getShippingAddress() == null || domain.getShippingAddress().isEmpty()) {
                throw new RuntimeException(SHIPPING_IS_REQUIRED);
            }
            if (domain.getBillingAddress() == null || domain.getBillingAddress().isEmpty()) {
                throw new RuntimeException(BILLING_IS_REQUIRED);
            }
            System.out.println(MODEL_ERROR + e.getMessage());
        }
        return order;
    }

    public Order placeOrder(Order order, String userId) {
        UserDomain uDomain = userRepo.findById(userId).get();
        List<Order> orders = new LinkedList<>();
        try {
            if (uDomain.getOrders() == null) {
                uDomain.setOrders(orders);
                orders.add(order);
                order.setOrderId(order.getOrderId());
                order.setCart(order.getCart());
                order.setCartTotal(order.getCartTotal());
                order.setShippingAddress(order.getShippingAddress());
                order.setBillingAddress(order.getBillingAddress());
                order.setUser(order.getUser());
            } else {
                uDomain.getOrders().add(order);
            }
        } catch (RuntimeException e) {
            if (uDomain == null) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
            if (uDomain.getUserId() == null || uDomain.getUserId().isEmpty()) {
                throw new RuntimeException(USER_ID_REQUIRED);
            }
            if (order.getUser() == null || order.getUser().isEmpty()) {
                throw new RuntimeException(USER_ID_REQUIRED);
            }
            if (order.getCart() == null || order.getCart().isEmpty()) {
                throw new RuntimeException(CART_REQUIRED);
            }
            if (order.getCartTotal() <= 0) {
                throw new RuntimeException(CART_GREATER_THAN_ZERO);
            }
            if (order.getShippingAddress() == null || order.getShippingAddress().isEmpty()) {
                throw new RuntimeException(SHIPPING_IS_REQUIRED);
            }
            if (order.getBillingAddress() == null || order.getBillingAddress().isEmpty()) {
                throw new RuntimeException(BILLING_IS_REQUIRED);
            }
            System.out.println(ORDER_ERROR + e.getMessage());
        }
        return mapDomainToModel(repo.save(mapModelToDomain(order)));
    }

    public void deleteOrder(String orderId, String userId) {
        UserDomain uDomain = userRepo.findById(userId).get();
        OrderDomain order = repo.findById(orderId).get();
        try {
            if (uDomain == null) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
            if (uDomain.getUserId() == null || uDomain.getUserId().isEmpty()) {
                throw new RuntimeException(USER_ID_REQUIRED);
            }
            if (order == null) {
                throw new RuntimeException(ORDER_NOT_FOUND);
            }
            if (order.getOrderId() == null || order.getOrderId().isEmpty()) {
                throw new RuntimeException(ORDER_ID_REQUIRED);
            }
            if (order.getUser() == null || order.getUser().isEmpty()) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
            int foundOrder = uDomain.getOrders().indexOf(findOrderById(orderId));
            Order n = uDomain.getOrders().get(foundOrder);
            uDomain.getOrders().remove(n);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        // repo.deleteById(orderId);
    }

    public Order findOrderById(String orderId) {
        Optional<OrderDomain> order = repo.findById(orderId);
        try {
            if (order == null) {
                throw new RuntimeException(ORDER_NOT_FOUND);
            }
            if (order.get().getOrderId() == null || order.get().getOrderId().isEmpty()) {
                throw new RuntimeException(ORDER_ID_REQUIRED);
            }
            if (order.get().getUser() == null || order.get().getUser().isEmpty()) {
                throw new RuntimeException(USER_NOT_FOUND);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return mapDomainToModel(null == order ? null : order.get());
    }

}
