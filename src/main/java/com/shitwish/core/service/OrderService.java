package com.shitwish.core.service;

import com.shitwish.core.model.Order;
import net.minidev.json.writer.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    public final String URI = "https://shitshop-order.herokuapp.com";

    public Order[] getOrderHistory(int userId) {
        String endPoint = "/orderhistory?userId=";
        String orderHistoryURI = URI + endPoint + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order[]> result = restTemplate.exchange(orderHistoryURI, HttpMethod.GET, entity, Order[].class);

        return result.getBody();
    }


}
