package com.portfolio.microservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicroservicesApplicationTests {
    // This test case is used to check if the Spring application context loads successfully

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        itemServiceTest test = new itemServiceTest();
        test.setUp();
    }

}
