package com.shitwish.core.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    public int order_id;
    private Date date;
    private ArrayList<Integer> products;

    public Order() {
    }

    public Order(int order_id, Date date, ArrayList<Integer> productList) {
        this.order_id = order_id;
        this.products = productList;
        this.date = date;
    }


    public ArrayList<Integer> getProducts() {
        return products;
    }

    public Date getDate() {
        return date;
    }

    public int getOrderId() {
        return order_id;
    }
}
