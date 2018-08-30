package com.shitwish.core.model;

public class Product {
    boolean isActive;
    boolean isInCart;
    int price;
    int userId;
    String name;
    String imgUrl;

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
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(boolean isActive, boolean isInCart, int price, int userId, String name, String imgUrl, String description) {

        this.isActive = isActive;
        this.isInCart = isInCart;
        this.price = price;
        this.userId = userId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    String description;

}
