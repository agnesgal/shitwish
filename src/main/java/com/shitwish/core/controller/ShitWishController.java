package com.shitwish.core.controller;

import com.shitwish.core.service.ShitWishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Controller
public class ShitWishController {

    @Autowired
    private ShitWishService shitWishService;

    private String USERAPIURL = "https://microservices-userapp.herokuapp.com/user";

    @GetMapping("/")
    public String loadIndex(){
        return "index";
    }

}