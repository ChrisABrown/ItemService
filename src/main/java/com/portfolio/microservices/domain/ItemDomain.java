package com.portfolio.microservices.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Items")
public class ItemDomain {

    @Id
    @JsonProperty("itemId")
    private String itemId = null;
    @JsonProperty("itemName")
    private String itemName = null;
    @JsonProperty("category")
    private String category = null;
    @JsonProperty("SKU")
    private String SKU = null;
    @JsonProperty("price")
    private BigDecimal price = null;
    @JsonProperty("description")
    private String description = null;
    @JsonProperty("image")
    private String image = null;
    // @JsonProperty("size")
    // @Valid
    // private List<SizeEnum> size = null;

}
