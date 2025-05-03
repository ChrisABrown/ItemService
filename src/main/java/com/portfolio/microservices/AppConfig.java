package com.portfolio.microservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.portfolio.microservices.repository.ItemRepository;
import com.portfolio.microservices.repository.OrderRepository;
import com.portfolio.microservices.repository.UserRepository;
import com.portfolio.microservices.service.AuthService;
import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.service.JwtService;
import com.portfolio.microservices.service.OrderService;
import com.portfolio.microservices.service.TokenBlacklistService;
import com.portfolio.microservices.service.UserService;

@Configuration
public class AppConfig {
    // Define your beans here

    private ItemRepository itemRepository;

    private UserRepository userRepository;

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

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

    @Bean
    public TokenBlacklistService tokenBlacklistService() {
        return new TokenBlacklistService();
    }

    @Bean
    public AuthService authService() {
        return new AuthService(userRepository, jwtService(), tokenBlacklistService());
    }
}
