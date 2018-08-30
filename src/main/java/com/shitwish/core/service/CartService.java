package com.shitwish.core.service;

import com.shitwish.core.model.Cart;
import com.shitwish.core.model.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    private Product product;

    @Autowired
    Parser jsonParser;

    public List<Product> getCartListById(int user_id) throws IOException {
        final String cartUrl = "https://shitshop-cart.herokuapp.com/cart/" + user_id;

        Cart cart = jsonParser.readJsonFromUrl(cartUrl, Cart.class);
        List<String> productStringIdList = cart.getCartList();
        List<Product> productList = new ArrayList<>();

        for (String product_id : productStringIdList) {
            String productUrl = "http://product-wishlist-byteme.herokuapp.com/product/" + product_id;
            Product product = jsonParser.readJsonFromUrlSplitted(productUrl, Product.class);
            System.out.println(product.toString());
            productList.add(product);
        }

        for (Product prod : productList) {
            System.out.println(prod.getName());
        }

        return productList;
    }

    public void addToCart(int user_id, int product_id) throws IOException {
        final String cartUrl = "https://shitshop-cart.herokuapp.com/cart/" + user_id + "/" + product_id;
        restTemplateBuilder.build().getForEntity(cartUrl, String.class);
    }
}
