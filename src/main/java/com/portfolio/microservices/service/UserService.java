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
    private final UserRepository repository;
    private final String PROFILE = "Main";
    private final String USER_NOT_FOUND = "User not found with id: ";
    private final String MODEL_ERROR_MESSAGE = "Error in in mapping model to domain: ";
    private final String DOMAIN_ERROR_MESSAGE = "Error in in mapping domain to model: ";

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    private User mapDomainToModel(UserDomain domain) {
        User newUser = new User();
        // Set the properties of newUser from domain
        // Use try-catch to handle any potential exceptions during mapping
        try {
            newUser.setUserId(domain.getUserId());
            newUser.setEmail(domain.getEmail());
            newUser.setMessages(domain.getMessages());
            newUser.setPassword(domain.getPassword());
            newUser.setProfile(domain.getProfile());
            newUser.setRole(newUser.getRole());
            newUser.setUsername(domain.getUsername());
            newUser.setOrders(domain.getOrders());
        } catch (RuntimeException e) {
            if (domain.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + domain.getUserId());
            }
            if (domain.getEmail() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getEmail());
            }
            if (domain.getMessages() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getMessages());
            }
            if (domain.getPassword() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getPassword());
            }
            if (domain.getProfile() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getProfile());
            }
            if (domain.getRole() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getRole());
            }
            if (domain.getUsername() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getUsername());
            }
            if (domain.getOrders() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + domain.getOrders());
            }
            throw new RuntimeException(DOMAIN_ERROR_MESSAGE + e.getMessage());
        }
        // Return the mapped model object
        return newUser;
    }

    private UserDomain mapModelToDomain(User user) {
        UserDomain domain = new UserDomain();
        // Set the properties of domain from user
        // Use try-catch to handle any potential exceptions during mapping
        try {
            domain.setUserId(user.getUserId());
            domain.setEmail(user.getEmail());
            domain.setMessages(user.getMessages());
            domain.setPassword(user.getPassword());
            domain.setProfile(user.getProfile());
            domain.setRole(user.getRole());
            domain.setUsername(user.getUsername());
            domain.setOrders(user.getOrders());
        } catch (RuntimeException e) {
            // Handle the case where user is null or has invalid properties
            if (user.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + user.getUserId());
            }
            if (user.getEmail() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getEmail());
            }
            if (user.getMessages() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getMessages());
            }
            if (user.getPassword() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getPassword());
            }
            if (user.getProfile() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getProfile());
            }
            if (user.getRole() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getRole());
            }
            if (user.getUsername() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getUsername());
            }
            if (user.getOrders() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + user.getOrders());
            }
            throw new RuntimeException(DOMAIN_ERROR_MESSAGE + e.getMessage());
        }
        // Return the mapped domain object
        return domain;
    }

    public User updateUser(User originUser) {
        UserDomain originUserDomain = mapModelToDomain(originUser);
        // Check if the user exists in the repository
        UserDomain foundUserDomain = repository.findById(originUserDomain.getUserId()).get();
        // If not, throw an exception or handle the error as needed
        // If the user exists, update the properties of originUserDomain
        // with the values from originUser
        // Save the updated user domain object back to the repository
        // and return the updated user model object
        // Use try-catch to handle any potential exceptions during the update process
        try {
            if (foundUserDomain == null) {
                throw new RuntimeException(USER_NOT_FOUND + originUserDomain.getUserId());
            }
            if (originUserDomain.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + originUserDomain.getUserId());
            }
            originUserDomain.setEmail(originUser.getEmail());
            originUserDomain.setMessages(originUser.getMessages());
            originUserDomain.setPassword(originUser.getPassword());
            originUserDomain.setProfile(originUser.getProfile());
            originUserDomain.setRole(originUserDomain.getRole());
            originUserDomain.setOrders(originUserDomain.getOrders());
            originUserDomain.setUsername(originUser.getUsername());
            // Save the updated user domain object back to the repository
            // and return the updated user model object
            return mapDomainToModel(repository.save(originUserDomain));
        } catch (RuntimeException e) {
            if (originUserDomain.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + originUserDomain.getUserId());
            } else if (originUserDomain.getEmail() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getEmail());
            } else if (originUserDomain.getMessages() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getMessages());
            } else if (originUserDomain.getPassword() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getPassword());
            } else if (originUserDomain.getProfile() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getProfile());
            } else if (originUserDomain.getRole() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getRole());
            } else if (originUserDomain.getUsername() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getUsername());
            } else if (originUserDomain.getOrders() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + originUserDomain.getOrders());
            }
            throw new RuntimeException(USER_NOT_FOUND + originUserDomain.getUserId());
        }
    }

    public User createUser(User bodyUser) {
        User newUser = new User();
        // Set the properties of newUser from bodyUser
        // Use try-catch to handle any potential exceptions during mapping
        try {
            newUser.setEmail(bodyUser.getEmail());
            newUser.setMessages(bodyUser.getMessages());
            newUser.setOrders(bodyUser.getOrders());
            newUser.setPassword(bodyUser.getPassword());
            newUser.setProfile(bodyUser.getProfile());
            newUser.setRole(bodyUser.getRole());
            newUser.setUserId(bodyUser.getUserId());
            newUser.setRole(bodyUser.getRole());
            newUser.setUsername(bodyUser.getUsername());
        } catch (RuntimeException e) {
            // Handle the case where bodyUser is null or has invalid properties
            if (bodyUser.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + bodyUser.getUserId());
            }
            if (bodyUser.getEmail() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getEmail());
            }
            if (bodyUser.getMessages() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getMessages());
            }
            if (bodyUser.getPassword() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getPassword());
            }
            if (bodyUser.getProfile() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getProfile());
            }
            if (bodyUser.getRole() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getRole());
            }
            if (bodyUser.getUsername() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getUsername());
            }
            if (bodyUser.getOrders() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + bodyUser.getOrders());
            }
            throw new RuntimeException(DOMAIN_ERROR_MESSAGE + e.getMessage());
        }
        // Save the newUser to the repository
        return mapDomainToModel(repository.save(mapModelToDomain(bodyUser)));
    }

    public void deleteUser(String userId) {
        UserDomain foundUserDomain = repository.findById(userId).get();

        repository.delete(foundUserDomain);
    }

    public Message createMessage(Message body, String userId) {
        User foundUser = mapDomainToModel(repository.findById(userId).get());
        foundUser.addMessagesItem(body);
        return foundUser.getMessages().get(foundUser.getMessages().size() - 1);
    }

    public void deleteMessage(String messageId) {
        repository.deleteById(messageId);
    }

    public Profile createProfile(String userId, Profile body) {
        User foundUser = mapDomainToModel(repository.findById(userId).get());
        Profile profile = new Profile();
        // Set the properties of profile from body
        try {
            profile.setAddress(body.getAddress());
            profile.setAvatar(body.getAvatar());
            profile.setBio(body.getBio());
            profile.setFirstName(body.getFirstName());
            profile.setLastName(body.getLastName());
        } catch (RuntimeException e) {
            if (body.getAddress() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + body.getAddress());
            }
            if (body.getAvatar() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + body.getAvatar());
            }
            if (body.getBio() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + body.getBio());
            }
            if (body.getFirstName() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + body.getFirstName());
            }
            if (body.getLastName() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + body.getLastName());
            }
        }
        // Add the profile to the user's profile list
        foundUser.putProfileItem(PROFILE, profile);
        // Save the updated user domain object back to the repository
        // and return the updated user model object
        return foundUser.getProfile().get(PROFILE);
    }

    public void deleteProfile(String userId) {
        User foundUser = mapDomainToModel(repository.findById(userId).get());
        // Check if the user exists in the repository
        if (foundUser == null) {
            throw new RuntimeException(USER_NOT_FOUND + userId);
        }
        // Check if the profile exists for the user
        if (foundUser.getProfile() == null || foundUser.getProfile().get(PROFILE) == null) {
            throw new RuntimeException(USER_NOT_FOUND + userId);
        }
        // Delete the profile from the user's profile list
        foundUser.getProfile().remove(PROFILE);
        // Save the updated user domain object back to the repository
        // and return the updated user model object
        // Use try-catch to handle any potential exceptions during the delete process
        try {
            repository.save(mapModelToDomain(foundUser));
        } catch (RuntimeException e) {
            if (foundUser.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + foundUser.getUserId());
            }
            if (foundUser.getProfile() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + foundUser.getProfile());
            }
            throw new RuntimeException(USER_NOT_FOUND + e.getMessage());
        }
        // Delete the profile from the repository
        // Use try-catch to handle any potential exceptions during the delete process
        // and return the updated user model object
        // Use try-catch to handle any potential exceptions during the delete process
        try {
            repository.deleteProfileByUserId(userId);
        } catch (RuntimeException e) {
            if (foundUser.getUserId() == null) {
                throw new RuntimeException(USER_NOT_FOUND + foundUser.getUserId());
            }
            if (foundUser.getProfile() == null) {
                throw new RuntimeException(MODEL_ERROR_MESSAGE + foundUser.getProfile());
            }
            throw new RuntimeException(USER_NOT_FOUND + e.getMessage());
        }
    }

    public User findUserById(String userId) {
        UserDomain foundUserDomain = repository.findById(userId).get();
        // Check if the user exists in the repository
        if (foundUserDomain == null) {
            throw new RuntimeException(USER_NOT_FOUND + userId);
        }
        // Map the found user domain object to a model object
        return mapDomainToModel(foundUserDomain);
    }
}
