package com.shitwish.core.controller;

import com.shitwish.core.model.Product;
import com.shitwish.core.service.JSONReader;
import com.sun.org.apache.xpath.internal.operations.Mod;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    JSONReader jsonReader = new JSONReader();

    @GetMapping("/")
    public String displayProducts(Model model){

        JSONObject allProductsJSON = jsonReader.getJson("localhost:50098/product");
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= allProductsJSON.length(); i++) {
            try {
                JSONObject productJSON = allProductsJSON.getJSONObject(String.valueOf(i));
                products.add(new Product(productJSON.getBoolean("is_active"),
                                         productJSON.getBoolean("is_incart"),
                                         productJSON.getInt("price"),
                                         productJSON.getInt("user_id"),
                                         productJSON.getString("name"),
                                         productJSON.getString("img"),
                                         productJSON.getString("descr")));
                model.addAttribute("products", products);
                return "index";
            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }
        }
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
        RestTemplate restTemplate = new RestTemplate();

        return "redirect:/";
    }
}
