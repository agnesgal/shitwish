package com.shitwish.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    public String id;
    boolean isActive;
    boolean isInCart;
    int price;
    int userId;
    String name;
    String img;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return img;
    }

    public void setImgUrl(String imgUrl) {
        this.img = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(String id, boolean isActive, boolean isInCart, int price, int userId, String name, String img, String description) {

        this.id = id;
        this.isActive = isActive;
        this.isInCart = isInCart;
        this.price = price;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.description = description;
    }

    String description;

}
