package com.shitwish.core.controller;

import com.shitwish.core.model.Order;
import com.shitwish.core.model.Product;
import com.shitwish.core.model.User;
import com.shitwish.core.service.OrderService;
import com.shitwish.core.service.ProductService;
import com.shitwish.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/{userId}/orderhistory")
    public String orderhistory( Model model, @PathVariable int userId) {
        model.addAttribute("user");
        Order[] orders = orderService.getOrderHistory(userId);
        HashMap<Order, List<Product>> orderHistory = null;
        for (Order order: orders) {
            Order key = order;
            List<Product> value = null;
            int[] productIds = order.getProductIds();
            for (int i = 0; i < productIds.length; i++) {
                Product product = new Product();
                value.add(product);
            }
            orderHistory.put(key, value);
        }

        model.addAttribute("order_history", orderHistory);
        return "order";
    }
}
