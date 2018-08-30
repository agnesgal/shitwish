package com.shitwish.core.model;

import java.util.ArrayList;

public class Cart {

    private int userId;
    private ArrayList<String> cartList;

    public int getUserId() {
        return userId;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public ArrayList<String> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<String> cartList) {
        this.cartList = cartList;
    }
}
