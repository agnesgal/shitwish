package com.shitwish.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @GetMapping("/")
    public String displayProducts(Model model){

        return "index";
    }

    @GetMapping("/product")
    public String displayOneProduct(Model model, @RequestParam("id") String id){

        return "product_view";
    }

}
