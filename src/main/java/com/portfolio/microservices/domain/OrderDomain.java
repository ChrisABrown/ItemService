package com.portfolio.microservices.domain;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.microservices.suprimeapi.model.Item;
import com.portfolio.microservices.suprimeapi.model.StreetAddress;
import com.portfolio.microservices.suprimeapi.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Orders")
public class OrderDomain {

    @Id
    @JsonProperty("orderId")
    private String orderId = null;
    @JsonProperty("User")
    @Valid
    private Map<String, User> user = null;
    @JsonProperty("billingAddress")
    @Valid
    private Map<String, StreetAddress> billingAddress = null;
    @JsonProperty("shippingAddress")
    @Valid
    private Map<String, StreetAddress> shippingAddress = null;
    @JsonProperty("Cart")
    @Valid
    private List<Item> cart = null;
    @JsonProperty("cartTotal")
    private Double cartTotal = null;
}