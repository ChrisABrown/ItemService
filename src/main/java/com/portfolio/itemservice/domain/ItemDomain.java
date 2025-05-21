package com.portfolio.itemservice.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Items")
public class ItemDomain {

    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("itemDomainId")
    @Field
    private UUID itemDomainId;
    @JsonProperty("itemName")
    @Field
    private String itemName;
    @JsonProperty("category")
    @Field
    private String category;
    @JsonProperty("SKU")
    @Field
    private String SKU;
    @JsonProperty("price")
    @Field
    private Double price;
    @JsonProperty("description")
    @Field
    private String description;
    @JsonProperty("image")
    @Field
    private String image;
    @JsonProperty("size")
    @Valid
    private List<String> size;
    @JsonProperty("color")
    @Valid
    private List<String> color;
    @JsonProperty("stock")
    @Field
    private Integer stock;

    public ItemDomain(String SKU, String category, List<String> color, String description, String id, String image, UUID itemDomainId, String itemName, Double price, List<String> size, Integer stock) {
        this.SKU = SKU;
        this.category = category;
        this.color = color;
        this.description = description;
        this.id = id;
        this.image = image;
        this.itemDomainId = itemDomainId;
        this.itemName = itemName;
        this.price = price;
        this.size = size;
        this.stock = stock;
    }

    ;
    public ItemDomain() {
        // Default constructor
    }
}
