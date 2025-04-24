package com.portfolio.microservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.UserService;
import com.portfolio.microservices.suprimeapi.api.UserApi;
import com.portfolio.microservices.suprimeapi.model.Message;
import com.portfolio.microservices.suprimeapi.model.User;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserService service;

    @Override
    public ResponseEntity<Void> createMessage(String userId, List<Message> body) {
        service.createMessage(body.getLast(), userId);
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public ResponseEntity<User> getUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public ResponseEntity<Void> updateUser(String userId) {
        User fUser = service.findUserById(userId);
        service.updateUser(fUser);
        return null;
    }

    @Override
    public ResponseEntity<User> createUser(User body) {
        User response = service.createUser(body);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<List<User>> createUsersWithArrayInput(List<User> body) {
        List<User> newUsers = new ArrayList<>();
        for (User user : body) {
            service.createUser(user);
            newUsers.add(user);
        }
        return ResponseEntity.ok().body(newUsers);
    }

}
