package com.shitwish.core.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @GetMapping("/")
    public String displayProducts(Model model){

        //TODO request to heroku product api, add result to model
        //List<Product> products = ???

        return "index";
    }

    @GetMapping("/product")
    public String displayOneProduct(Model model, @RequestParam("id") String id){

        //TODO request to heroku product api, add result to model
        //Product product = ???

        return "product_view";
    }

    @GetMapping("/add")
    public String showNewProductForm(){

        return "product_form";
    }

    @PostMapping("/add")
    public String addNewProduct(){


        return "redirect:/";
    }
}
