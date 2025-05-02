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
        // Transform the body to an array
        // to get the first element
        // and cast it to a Message object
        // This is a workaround for the fact that
        // the body is a list of messages
        // and we only want the first one
        // to create a message
        Object[] messageArr = body.toArray();
        Message message = (Message) messageArr[0];
        // Check if the message is null or empty
        try {
            if (body.size() < 1) {
                return ResponseEntity.badRequest().build();
            }
            if (userId == null || userId.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            service.createMessage(message, userId);
        // Check if the user exists
        User foundUser = service.findUserById(userId);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        // Add the message to the user's messages
        foundUser.getMessages().add(message);
        // Update the user with the new message
        service.updateUser(foundUser).addMessagesItem(message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
         // Check if the user exists
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
