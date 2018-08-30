package com.shitwish.core.controller;

import com.shitwish.core.model.Order;
import com.shitwish.core.model.User;
import com.shitwish.core.service.OrderService;
import com.shitwish.core.service.ProductService;
import com.shitwish.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping(value = "/order")
    public String orderhistory( Model model) {
        model.addAttribute("user");
        Order[] orders = orderService.getOrderHistory(1);
        model.addAttribute("order_history", orders);
        return "index";
    }
}
