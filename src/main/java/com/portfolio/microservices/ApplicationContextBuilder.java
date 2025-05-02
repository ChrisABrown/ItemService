package com.portfolio.microservices;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBuilder {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            // Example: List all bean names
            String[] beanNames = context.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        }
    }
}
