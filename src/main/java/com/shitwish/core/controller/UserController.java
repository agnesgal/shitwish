package com.shitwish.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/{id}/profile")
    public String getUserProfile() {
        return "user_profile";
    }

}
