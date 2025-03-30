package com.portfolio.microservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.portfolio.microservices.domain.UserDomain;

public interface UserRepository extends MongoRepository<UserDomain, String> {

}
