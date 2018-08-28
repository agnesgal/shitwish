package com.shitwish.core.controller;

import com.shitwish.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping(value = "/order")
    public String orderView( Model model) {
        model.addAttribute("order", orderService.getOrder());
        return "order";
    }
}
