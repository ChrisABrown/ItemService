package com.portfolio.microservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.microservices.service.UserService;
import com.portfolio.microservices.suprimeapi.api.UserApi;
import com.portfolio.microservices.suprimeapi.model.Message;
import com.portfolio.microservices.suprimeapi.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController implements UserApi {

    @Autowired
    UserService service;

    @Override
    @PostMapping("/{userId}/message")
    public ResponseEntity<Void> createMessage(@PathVariable String userId, List<Message> body) {
        service.createMessage(body.getLast(), userId);
        return null;
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
       service.deleteUser(userId);
       return null;
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
       User foundUser = service.findUserById(userId);

       return ResponseEntity.ok().body(foundUser);
    }

    @Override
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId) {
        User fUser = service.findUserById(userId);
        service.updateUser(fUser);
        return null;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<User> createUser(User body) {
        User response = service.createUser(body);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @PostMapping("/createWithArray")
    public ResponseEntity<List<User>> createUsersWithArrayInput(List<User> body) {
        List<User> newUsers = new ArrayList<>();
        for (User user : body) {
            service.createUser(user);
            newUsers.add(user);
        }
        return ResponseEntity.ok().body(newUsers);
    }

}
