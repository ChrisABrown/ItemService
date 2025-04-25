package com.portfolio.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.microservices.domain.UserDomain;
import com.portfolio.microservices.repository.UserRepository;
import com.portfolio.microservices.suprimeapi.model.Message;
import com.portfolio.microservices.suprimeapi.model.Profile;
import com.portfolio.microservices.suprimeapi.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private User mapDomainToModel(UserDomain domain) {
        User newUser = new User();
        newUser.setEmail(domain.getEmail());
        newUser.setMessages(domain.getMessages());
        newUser.setPassword(domain.getPassword());
        newUser.setProfile(domain.getProfile());
        newUser.setRole(newUser.getRole());
        newUser.setUsername(domain.getUsername());
        newUser.setOrders(domain.getOrders());

        return newUser;
    }

    private UserDomain mapModelToDomain(User user) {
        UserDomain domain = new UserDomain();
        domain.setEmail(user.getEmail());
        domain.setMessages(user.getMessages());
        domain.setProfile(user.getProfile());
        domain.setUsername(user.getUsername());
        domain.setPassword(user.getPassword());
        domain.setRole(domain.getRole());
        domain.setUserId(domain.getUserId());
        domain.setOrders(domain.getOrders());

        return domain;
    }

    public User updateUser(User originUser) {
        UserDomain originUserDomain = mapModelToDomain(originUser);
        UserDomain foundUserDomain = repository.findById(originUserDomain.getUserId()).get();

        if (originUserDomain.getUserId() == null ? foundUserDomain.getUserId() == null : originUserDomain.getUserId().equals(foundUserDomain.getUserId())) {
            originUserDomain.setEmail(originUser.getEmail());
            originUserDomain.setMessages(originUser.getMessages());
            originUserDomain.setPassword(originUser.getPassword());
            originUserDomain.setProfile(originUser.getProfile());
            originUserDomain.setRole(originUserDomain.getRole());
            originUserDomain.setOrders(originUserDomain.getOrders());

        } else {
            return mapDomainToModel(repository.save(mapModelToDomain(originUser)));
        }
        return mapDomainToModel(repository.insert(originUserDomain));
    }

    public User createUser(User bodyUser) {
        User newUser = new User();
        newUser.setEmail(bodyUser.getEmail());
        newUser.setMessages(bodyUser.getMessages());
        newUser.setOrders(bodyUser.getOrders());
        newUser.setPassword(bodyUser.getPassword());
        newUser.setProfile(bodyUser.getProfile());
        newUser.setRole(bodyUser.getRole());
        newUser.setUserId(bodyUser.getUserId());
        newUser.setRole(bodyUser.getRole());
        return mapDomainToModel(repository.save(mapModelToDomain(bodyUser)));

    }

    public void deleteUser(String userId) {
        UserDomain foundUserDomain = repository.findById(userId).get();

        repository.delete(foundUserDomain);
    }

    public Message createMessage(Message body, String userId) {
        User foundUser = mapDomainToModel(repository.findById(userId).get());
        foundUser.addMessagesItem(body);
        return foundUser.getMessages().getLast();
    }

    public void deleteMessage(String messageId) {
        repository.deleteById(messageId);
    }

    public Profile createProfile(String userId, Profile body) {
        User foundUser = mapDomainToModel(repository.findById(userId).get());
        Profile profile = new Profile();
        foundUser.putProfileItem("Main", profile);
        profile.setAddress(body.getAddress());
        profile.setAvatar(body.getAvatar());
        profile.setBio(body.getBio());
        profile.setFirstName(body.getFirstName());
        profile.setLastName(body.getLastName());

        return foundUser.getProfile().get("Main");
    }

    public void deleteProfile(String userId) {
        repository.deleteProfileByUserId(userId);
    }

    public User findUserById(String userId) {
        return mapDomainToModel(repository.findById(userId).get());
    }
}
