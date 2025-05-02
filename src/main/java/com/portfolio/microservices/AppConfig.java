package com.portfolio.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.portfolio.microservices.repository.ItemRepository;
import com.portfolio.microservices.repository.OrderRepository;
import com.portfolio.microservices.repository.UserRepository;
import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.service.OrderService;
import com.portfolio.microservices.service.UserService;

@Configuration
public class AppConfig {
    // Define your beans here

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepository, userRepository);
    }
}
