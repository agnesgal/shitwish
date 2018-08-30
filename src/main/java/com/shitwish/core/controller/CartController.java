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
        model.addAttribute("productList", cartService.getCartListById(user_id));
        return "index";
    }

    @GetMapping("/addToCart/{user_id}/{product_id}")
    public String addToCart(@PathVariable("user_id") int user_id, @PathVariable("product_id") int product_id) throws IOException {
        cartService.addToCart(user_id, product_id);
        return "redirect:/cart/" + user_id;
    }

    /*@GetMapping("/welcome")
    public String welcome() throws IOException {
        return "welcome";
    }*/

    /*@GetMapping("/checkout")
    public String checkout() throws IOException {
        return "checkout";
    }

    @GetMapping("/card")
    public String payment(Model model) throws IOException {
        model.addAttribute("cartPrices", "45");
        return "card";
    }
}
