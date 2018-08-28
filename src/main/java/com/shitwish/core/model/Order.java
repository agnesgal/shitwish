package com.shitwish.core.model;

import java.util.Date;
import java.util.List;

public class Order {

    private User user;
    private List<Product> productList;
    Date date;
    String tempString;

    public Order(User user, List<Product> productList, Date date) {
        this.user = user;
        this.productList = productList;
        this.date = date;
    }

    public Order(String tempString) {
        this.tempString = tempString;
    }

    public String getTempString() {
        return tempString;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
