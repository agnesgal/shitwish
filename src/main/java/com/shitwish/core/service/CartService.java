package com.shitwish.core.service;

import com.shitwish.core.model.Cart;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    Parser jsonParser;

//    public String getCartList() throws IOException {
//        return jsonParser.readJsonObFromUrl("https://shitshop-cart.herokuapp.com/cart").get("carList").toString();
//    }

    public List<String> getCartListById(int user_id) throws IOException {
        final String cartUrl = "https://shitshop-cart.herokuapp.com/cart/" + user_id;
        Cart cart = jsonParser.readJsonFromUrl(cartUrl, Cart.class);
        return cart.getCartList();
    }

    public JSONObject addToCart(int user_id, int product_id) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("user_id", user_id);
        obj.put("product_id", product_id);
        return obj;
    }
}
