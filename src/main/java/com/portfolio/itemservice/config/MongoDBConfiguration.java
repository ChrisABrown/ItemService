package com.portfolio.itemservice.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@ComponentScan(basePackages = "com.portfolio.itemservice")
public class MongoDBConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String db;

    @Value("${spring.data.mongodb.authentication-database}")
    private String authDB;

    @Bean
    public MongoTemplate mongoTemplate()
            throws UnknownHostException, java.net.UnknownHostException {
        MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(
                mongoClient(),
                getDatabaseName());
        return new MongoTemplate(mongoDbFactory);
    }

    @Override
    protected String getDatabaseName() {
        return db;
    }

}
