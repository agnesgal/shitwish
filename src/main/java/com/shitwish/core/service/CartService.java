package com.shitwish.core.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CartService {

    @Autowired
    private Parser jsonParser;

    @GetMapping("/cart/{user_id}")
    public String getCartList(@PathVariable("user_id") int user_id) throws IOException {
        return jsonParser.readJsonFromUrl("https://shitshop-cart.herokuapp.com/cart/" + user_id).get("cartList").toString();
    }

    @PostMapping("addToCart/{user_id}/{product_id}")
    public JSONObject addToCart(@PathVariable("user_id") int user_id, @PathVariable("product") int product_id) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("user_id", user_id);
        obj.put("product_id", product_id);
        return obj;
    }
}
