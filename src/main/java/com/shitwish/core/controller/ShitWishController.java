package com.shitwish.core.controller;

import com.shitwish.core.service.JSONReader;
import com.shitwish.core.service.ShitWishService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebParam;
import java.io.IOException;

@Controller
public class ShitWishController {

    @Autowired
    private ShitWishService shitWishService;

    private String USERAPIURL = "https://microservices-userapp.herokuapp.com/user";
    JSONReader jsonReader = new JSONReader();
    public JSONObject userTestObject = jsonReader.getJson(USERAPIURL);

    @GetMapping("/")
    public String loadIndex(Model model){
        try {
            String testname = userTestObject.get("name").toString();
            model.addAttribute("jsonbullshit2", testname);
        } catch (JSONException ex){
            model.addAttribute("jsonbullshit2", "something went wrong");
            ex.printStackTrace();
        }
        model.addAttribute("jsonbullshit", userTestObject);

        return "index";
    }

}