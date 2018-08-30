package com.shitwish.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int userId;
    private ArrayList<Integer> productIdList;
    Date date;
    String tempString;

    public Order(int userId, ArrayList<Integer> productList, Date date) {
        this.userId = userId;
        this.productIdList = productList;
        this.date = date;
    }

    public Order(String tempString) {
        this.tempString = tempString;
    }

    public String getTempString() {
        return tempString;
    }

    public ArrayList<Integer> getProductIdList() {
        return productIdList;
    }

    public Date getDate() {
        return date;
    }

    public int getUser() {
        return userId;
    }
}
