package com.portfolio.microservices.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.portfolio.microservices.suprimeapi.model.Item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Items")
public class ItemDomain extends Item {

    // @Id
    // @JsonProperty("itemId")
    // private String itemId;
    // @JsonProperty("itemName")
    // private String itemName;
    // @JsonProperty("category")
    // private String category;
    // @JsonProperty("SKU")
    // private String SKU;
    // @JsonProperty("price")
    // private Double price;
    // @JsonProperty("description")
    // private String description;
    // @JsonProperty("image")
    // private String image;
    // @JsonProperty("size")
    // @Valid
    // private List<String> size;
    // @JsonProperty("color")
    // @Valid
    // private List<String> color;
    // @JsonProperty("stock")
    // private Integer stock;
}
