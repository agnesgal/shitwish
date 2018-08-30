package com.shitwish.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
public class UserController {

   // @GetMapping("/user/{01}/profile")
    @GetMapping("/")
    public String getUserProfile(Model model, HttpServletRequest request) throws JSONException{

//        JSONObject userData = new JSONObject(readFromUrl("https://microservices-userapp.herokuapp.com/sampleuser"));
//
//        String profilePicture = userData.getString("profilePicture");
//        String firstName = userData.getString("firstName");
//        String lastName = userData.getString("lastName");
//        String phoneNum = userData.getString("phoneNumber");
//        String country = userData.getString("country");
//        String zip = userData.getString("zipCode");
//        String city = userData.getString("city");
//        String street = userData.getString("street");
////      String usrID = userData.getString("usrID");
//        String email = userData.getString("email");

        HashMap[] resultArr;

        try {
            String cucc = URLEncoder.encode((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), "UTF-8");

            HttpResponse<String> response = Unirest.get("https://teammate.eu.auth0.com/api/v2/users?q=user_id=" + cucc + "&search_engine=v3")
                    .header("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik5VUTBOa1ZEUXpWRVJEUkRNMFF5TmtSRE16Z3pNekZCUWtNNU1rTXhOMEV5T0VORk56YzROQSJ9.eyJpc3MiOiJodHRwczovL3RlYW1tYXRlLmV1LmF1dGgwLmNvbS8iLCJzdWIiOiI5UXdZTWJBdVlrSjB1dGZxdjQwb1d6T2hMZk1Sbkhzb0BjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly90ZWFtbWF0ZS5ldS5hdXRoMC5jb20vYXBpL3YyLyIsImlhdCI6MTUzNTYzMDgyOCwiZXhwIjoxNTM1NzE3MjI4LCJhenAiOiI5UXdZTWJBdVlrSjB1dGZxdjQwb1d6T2hMZk1SbkhzbyIsInNjb3BlIjoicmVhZDpjbGllbnRfZ3JhbnRzIGNyZWF0ZTpjbGllbnRfZ3JhbnRzIGRlbGV0ZTpjbGllbnRfZ3JhbnRzIHVwZGF0ZTpjbGllbnRfZ3JhbnRzIHJlYWQ6dXNlcnMgdXBkYXRlOnVzZXJzIGRlbGV0ZTp1c2VycyBjcmVhdGU6dXNlcnMgcmVhZDp1c2Vyc19hcHBfbWV0YWRhdGEgdXBkYXRlOnVzZXJzX2FwcF9tZXRhZGF0YSBkZWxldGU6dXNlcnNfYXBwX21ldGFkYXRhIGNyZWF0ZTp1c2Vyc19hcHBfbWV0YWRhdGEgY3JlYXRlOnVzZXJfdGlja2V0cyByZWFkOmNsaWVudHMgdXBkYXRlOmNsaWVudHMgZGVsZXRlOmNsaWVudHMgY3JlYXRlOmNsaWVudHMgcmVhZDpjbGllbnRfa2V5cyB1cGRhdGU6Y2xpZW50X2tleXMgZGVsZXRlOmNsaWVudF9rZXlzIGNyZWF0ZTpjbGllbnRfa2V5cyByZWFkOmNvbm5lY3Rpb25zIHVwZGF0ZTpjb25uZWN0aW9ucyBkZWxldGU6Y29ubmVjdGlvbnMgY3JlYXRlOmNvbm5lY3Rpb25zIHJlYWQ6cmVzb3VyY2Vfc2VydmVycyB1cGRhdGU6cmVzb3VyY2Vfc2VydmVycyBkZWxldGU6cmVzb3VyY2Vfc2VydmVycyBjcmVhdGU6cmVzb3VyY2Vfc2VydmVycyByZWFkOmRldmljZV9jcmVkZW50aWFscyB1cGRhdGU6ZGV2aWNlX2NyZWRlbnRpYWxzIGRlbGV0ZTpkZXZpY2VfY3JlZGVudGlhbHMgY3JlYXRlOmRldmljZV9jcmVkZW50aWFscyByZWFkOnJ1bGVzIHVwZGF0ZTpydWxlcyBkZWxldGU6cnVsZXMgY3JlYXRlOnJ1bGVzIHJlYWQ6cnVsZXNfY29uZmlncyB1cGRhdGU6cnVsZXNfY29uZmlncyBkZWxldGU6cnVsZXNfY29uZmlncyByZWFkOmVtYWlsX3Byb3ZpZGVyIHVwZGF0ZTplbWFpbF9wcm92aWRlciBkZWxldGU6ZW1haWxfcHJvdmlkZXIgY3JlYXRlOmVtYWlsX3Byb3ZpZGVyIGJsYWNrbGlzdDp0b2tlbnMgcmVhZDpzdGF0cyByZWFkOnRlbmFudF9zZXR0aW5ncyB1cGRhdGU6dGVuYW50X3NldHRpbmdzIHJlYWQ6bG9ncyByZWFkOnNoaWVsZHMgY3JlYXRlOnNoaWVsZHMgZGVsZXRlOnNoaWVsZHMgdXBkYXRlOnRyaWdnZXJzIHJlYWQ6dHJpZ2dlcnMgcmVhZDpncmFudHMgZGVsZXRlOmdyYW50cyByZWFkOmd1YXJkaWFuX2ZhY3RvcnMgdXBkYXRlOmd1YXJkaWFuX2ZhY3RvcnMgcmVhZDpndWFyZGlhbl9lbnJvbGxtZW50cyBkZWxldGU6Z3VhcmRpYW5fZW5yb2xsbWVudHMgY3JlYXRlOmd1YXJkaWFuX2Vucm9sbG1lbnRfdGlja2V0cyByZWFkOnVzZXJfaWRwX3Rva2VucyBjcmVhdGU6cGFzc3dvcmRzX2NoZWNraW5nX2pvYiBkZWxldGU6cGFzc3dvcmRzX2NoZWNraW5nX2pvYiByZWFkOmN1c3RvbV9kb21haW5zIGRlbGV0ZTpjdXN0b21fZG9tYWlucyBjcmVhdGU6Y3VzdG9tX2RvbWFpbnMgcmVhZDplbWFpbF90ZW1wbGF0ZXMgY3JlYXRlOmVtYWlsX3RlbXBsYXRlcyB1cGRhdGU6ZW1haWxfdGVtcGxhdGVzIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.lF4y6bsYSFDPuZxLKTIMLQ2JApO_8wHc7L1hUblnMMN0OVJp7fwD9P6ek8C4nEfFRCK8-2qf3nFX98Vh51H4MJfgvsf45rRz9gtJtftbAis343fv2eTbx874WOJaZPDsiwaA7pHiHZg96KCYiT12dp87VAmVY3a8HLa6DxjngJbk2EcHIU2dXS7d8OA31UlTaAdz_SJ9k_gwcMySuAVD6w8se1AM50P6EMBXrhT5Honr9LJBDlb8Z67l6MmwobYaMe1W8AwtLpIpUaWQ20GtIyurNqXnn3wj6VgFSnsM9vG2Uf3rYXu-gf47LiPklyF2cIjyRl7ILbjAN4sAqna66Q")
                    .asString();

            resultArr = new ObjectMapper().readValue(response.getBody(), HashMap[].class);
            HashMap result = resultArr[0];

            model.addAttribute("userName", result.get("name"));
            model.addAttribute("profilePicture", result.get("picture"));
            model.addAttribute("phoneNum", "");
            model.addAttribute("country", "");
            model.addAttribute("zip", "");
            model.addAttribute("city", "");
            model.addAttribute("street", "");
//      model.addAttribute("usrID", usrID);
            model.addAttribute("email", result.get("email"));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return "index";
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