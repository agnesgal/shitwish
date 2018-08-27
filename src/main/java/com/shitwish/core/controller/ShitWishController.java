package com.shitwish.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShitWishController {

    @GetMapping("/")
    public String loadIndex(){
        return "index";
    }

}