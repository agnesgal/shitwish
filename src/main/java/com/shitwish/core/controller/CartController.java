package com.shitwish.core.controller;

import com.shitwish.core.service.CartService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.IOException;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart/{user_id}")
    public String getCartListById(@PathVariable ("user_id") int user_id, Model model) throws IOException {
        model.addAttribute("cartListById", cartService.getCartListById(user_id));
        return "index";
    }

    @PostMapping("addToCart/{user_id}/{product_id}")
    public JSONObject addToCart(@PathVariable("user_id") int user_id, @PathVariable("product") int product_id) throws IOException {
        return cartService.addToCart(user_id, product_id);
    }

    @GetMapping("/checkout")
    public String checkout() throws IOException {
        return "checkout";
    }

    @GetMapping("/zsindex")
    public String zsindex() throws IOException {
        return "zsindex";
    }
}
