package com.shitwish.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.shitwish.core.model.Product;
import com.shitwish.core.service.JSONReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class ProductController {

    JSONReader jsonReader = new JSONReader();

    @GetMapping("/")
    public String displayProducts(Model model){

        JSONObject allProductsJSON = jsonReader.getJson("http://product-wishlist-byteme.herokuapp.com/product");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allProductsJSON.length(); i++) {
            try {
                Set keys = null;
                keys = allProductsJSON.keySet();
                Object[] keyList = keys.toArray();

                JSONObject productJSON = allProductsJSON.getJSONObject(String.valueOf(keyList[i]));
                products.add(new Product(productJSON.getBoolean("is_active"),
                                         productJSON.getBoolean("is_incart"),
                                         productJSON.getInt("price"),
                                         productJSON.getInt("user_id"),
                                         productJSON.getString("name"),
                                         productJSON.getString("img"),
                                         productJSON.getString("descr")));

            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }
        }
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product")
    public String displayOneProduct(Model model, @RequestParam("id") String id){
        try {
            JSONObject productJSON = jsonReader.getJson("http://product-wishlist-byteme.herokuapp.com/product" + id);
            Product product = new Product(productJSON.getBoolean("is_active"),
                    productJSON.getBoolean("is_incart"),
                    productJSON.getInt("price"),
                    productJSON.getInt("user_id"),
                    productJSON.getString("name"),
                    productJSON.getString("img"),
                    productJSON.getString("descr"));

            model.addAttribute("product", product);
            return "product_view";
        } catch (JSONException ex){
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/add")
    public String showNewProductForm(){ return "product_form"; }

    @PostMapping("/add")
    public String addNewProduct(@RequestBody MultiValueMap<String, String> formData){

        try {
            Map newForm = formData.toSingleValueMap();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(newForm, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity( "https://product-wishlist-byteme.herokuapp.com/product", request , String.class );
            System.out.println(newForm.toString());
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }



    }

}
