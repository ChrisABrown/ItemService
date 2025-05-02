package com.portfolio.microservices.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.microservices.domain.UserDomain;
import com.portfolio.microservices.suprimeapi.model.User;

@Repository
public interface UserRepository extends MongoRepository<UserDomain, String> {

    @Query("{ 'messageId': ?0 }")
    String findByMessId(String messageId);

    @DeleteQuery("{'userId': ?0}")
    void deleteProfileByUserId(String userId);

    @Query("{ 'email': ?0 }")
    User findByEmail(String email);

}
