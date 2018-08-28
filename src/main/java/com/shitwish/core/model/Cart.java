package com.shitwish.core.model;

import java.util.ArrayList;

public class Cart {

    private User user;
    private ArrayList<Integer> productList;


    public ArrayList<Integer> getProductList() {
        return productList;
    }

    public void addProduct(int productId) {
        productList.add(productId);
    }

    public Cart(User user, ArrayList<Integer> productList) {
        this.user = user;
        this.productList = productList;
    }
}
