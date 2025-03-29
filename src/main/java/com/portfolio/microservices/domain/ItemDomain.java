package com.portfolio.microservices.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.microservices.suprimeapi.model.Item.SizeEnum;

@Document(collection = "Items")
public class ItemDomain {

    @Id
    private String itemId = null;
    private String itemName = null;
    private String category = null;
    private String SKU = null;
    private BigDecimal price = null;
    private String description = null;
    private String image = null;
    private List<SizeEnum> size = null;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        SKU = sKU;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<SizeEnum> getSize() {
        return size;
    }

    public void setSize(List<SizeEnum> size) {
        this.size = size;
    }

}
