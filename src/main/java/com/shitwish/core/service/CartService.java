package com.shitwish.core.service;

import com.shitwish.core.controller.CartController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Service
public class CartService {

    @Autowired
    Parser jsonParser;

    public String getCartList() throws IOException {
        return jsonParser.readJsonFromUrl("https://shitshop-cart.herokuapp.com/cart").get("carList").toString();
    }

//    public JSONObject addToCart(int user_id, int product_id) throws IOException {
//        JSONObject obj = new JSONObject();
//        obj.put("user_id", user_id);
//        obj.put("product_id", product_id);
//        return obj;
//    }
}
