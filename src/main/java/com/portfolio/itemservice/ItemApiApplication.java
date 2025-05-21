package com.portfolio.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ItemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemApiApplication.class, args);
    }

}
