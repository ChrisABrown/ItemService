package com.portfolio.microservices;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.itemservice.ItemApiApplication;
import com.portfolio.itemservice.service.ItemService;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ItemApiApplication.class
)
class MicroservicesApplicationTests {
    // This test case is used to check if the Spring application context loads successfully

    @Autowired
    ItemService itemService = null;
    // Autowire the ItemService to test its functionality

    @Test
    void contextLoads() {
        // This method is intentionally left empty. If the context fails to load, an exception will be thrown.
        // You can add assertions here if you want to check specific beans or configurations.
        assertNotNull(itemService, "ItemService should not be null");
    }

    @Test
    void test() {
        if (itemService != null) {
            throw new AssertionError("ItemService is not loaded");
        }
    }
}
