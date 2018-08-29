package com.shitwish.core.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class UserController {

   // @GetMapping("/user/{01}/profile")
    @GetMapping("/")
    public String getUserProfile(Model model) throws JSONException{

        JSONObject userData = new JSONObject(readFromUrl("https://microservices-userapp.herokuapp.com/sampleuser"));

        String profilePicture = userData.getString("profilePicture");
        String firstName = userData.getString("firstName");
        String lastName = userData.getString("lastName");
        String phoneNum = userData.getString("phoneNumber");
        String country = userData.getString("country");
        String zip = userData.getString("zipCode");
        String city = userData.getString("city");
        String street = userData.getString("street");
//      String usrID = userData.getString("usrID");
        String email = userData.getString("email");

        model.addAttribute("userName", firstName + " " + lastName);
        model.addAttribute("profilePicture", profilePicture);
        model.addAttribute("phoneNum", phoneNum);
        model.addAttribute("country", country);
        model.addAttribute("zip", zip);
        model.addAttribute("city", city);
        model.addAttribute("street", street);
//      model.addAttribute("usrID", usrID);
        model.addAttribute("email", email);

        return "user_profile";
    }

    private String readFromUrl(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content.toString();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}


/* logout
    editUserData
    viewOrderHistory

 */