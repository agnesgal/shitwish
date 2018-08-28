package com.shitwish.core.model;

import java.util.Date;

public class Order {

    int userId;
    int[] productIds;
    Date date;
    String tempString;

    public Order(int userId, int[] productIds, Date date) {
        this.userId = userId;
        this.productIds = productIds;
        this.date = date;
    }

    public Order(String tempString) {
        this.tempString = tempString;
    }

    public String getTempString() {
        return tempString;
    }

    public int[] getProductIds() {
        return productIds;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }


}
