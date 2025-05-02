package com.portfolio.microservices.domain;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.microservices.suprimeapi.model.Message;
import com.portfolio.microservices.suprimeapi.model.Order;
import com.portfolio.microservices.suprimeapi.model.Profile;
import com.portfolio.microservices.suprimeapi.model.RoleEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Users")
public class UserDomain {

    @Id
    @JsonProperty("userId")
    private String userId = null;
    @JsonProperty("username")
    private String username = null;
    @JsonProperty("role")
    public RoleEnum role = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("profile")
    @Valid
    private Map<String, Profile> profile = null;

    @JsonProperty("messages")
    @Valid
    private List<Message> messages = null;

    @JsonProperty("orders")
    @Valid
    private List<Order> orders = null;

}
