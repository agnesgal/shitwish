package com.shitwish.core.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private int orderId;
    private Date date;
    private int[] productIds;

    public Order() {
    }

    public Order(int orderId, Date date, int[] productIds) {
        this.orderId = orderId;
        this.productIds = productIds;
        this.date = date;
    }


    public int[] getProductIds() {
        return productIds;
    }

    public Date getDate() {
        return date;
    }

    public int getOrderId() {
        return orderId;
    }
}
