package com.portfolio.microservices.domain;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.microservices.suprimeapi.model.Item;
import com.portfolio.microservices.suprimeapi.model.OrderShippingAddress;
import com.portfolio.microservices.suprimeapi.model.Profile;
import com.portfolio.microservices.suprimeapi.model.User;

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
    private Map<String, Profile> billingAddress = null;
    @JsonProperty("shippingAddress")
    private OrderShippingAddress shippingAddress = null;
    @JsonProperty("cart")
    @Valid
    private List<Item> cart = null;
    @JsonProperty("cartTotal")
    private Double cartTotal = null;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<String, User> getUser() {
        return user;
    }

    public void setUser(Map<String, User> user) {
        this.user = user;
    }

    public Map<String, Profile> getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Map<String, Profile> billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(OrderShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }

}
