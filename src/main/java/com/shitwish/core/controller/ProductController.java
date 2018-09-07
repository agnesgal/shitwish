package com.shitwish.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.shitwish.core.model.Product;
import com.shitwish.core.service.JSONReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.*;

@Controller
public class ProductController {

    JSONReader jsonReader = new JSONReader();

    @GetMapping("/products")
    public String displayProducts(Model model){

        JSONObject allProductsJSON = jsonReader.getJson("http://product-wishlist-byteme.herokuapp.com/product");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allProductsJSON.length(); i++) {
            try {
                Set keys = null;
                keys = allProductsJSON.keySet();
                Object[] keyList = keys.toArray();

                JSONObject productJSON = allProductsJSON.getJSONObject(String.valueOf(keyList[i]));
                products.add(new Product(String.valueOf(keyList[i]),
                                         productJSON.getBoolean("is_active"),
                                         productJSON.getBoolean("is_incart"),
                                         productJSON.getInt("price"),
                                         productJSON.getInt("user_id"),
                                         productJSON.getString("name"),
                                         productJSON.getString("img"),
                                         productJSON.getString("descr")));

            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }

        }
        model.addAttribute("products", products);
        HashMap[] resultArr;

        try {
            String cucc = URLEncoder.encode((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), "UTF-8");

            HttpResponse<String> response = Unirest.get("https://teammate.eu.auth0.com/api/v2/users?q=user_id=" + cucc + "&search_engine=v3")
                    .header("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik5VUTBOa1ZEUXpWRVJEUkRNMFF5TmtSRE16Z3pNekZCUWtNNU1rTXhOMEV5T0VORk56YzROQSJ9.eyJpc3MiOiJodHRwczovL3RlYW1tYXRlLmV1LmF1dGgwLmNvbS8iLCJzdWIiOiI5UXdZTWJBdVlrSjB1dGZxdjQwb1d6T2hMZk1Sbkhzb0BjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly90ZWFtbWF0ZS5ldS5hdXRoMC5jb20vYXBpL3YyLyIsImlhdCI6MTUzNTYzMDgyOCwiZXhwIjoxNTM1NzE3MjI4LCJhenAiOiI5UXdZTWJBdVlrSjB1dGZxdjQwb1d6T2hMZk1SbkhzbyIsInNjb3BlIjoicmVhZDpjbGllbnRfZ3JhbnRzIGNyZWF0ZTpjbGllbnRfZ3JhbnRzIGRlbGV0ZTpjbGllbnRfZ3JhbnRzIHVwZGF0ZTpjbGllbnRfZ3JhbnRzIHJlYWQ6dXNlcnMgdXBkYXRlOnVzZXJzIGRlbGV0ZTp1c2VycyBjcmVhdGU6dXNlcnMgcmVhZDp1c2Vyc19hcHBfbWV0YWRhdGEgdXBkYXRlOnVzZXJzX2FwcF9tZXRhZGF0YSBkZWxldGU6dXNlcnNfYXBwX21ldGFkYXRhIGNyZWF0ZTp1c2Vyc19hcHBfbWV0YWRhdGEgY3JlYXRlOnVzZXJfdGlja2V0cyByZWFkOmNsaWVudHMgdXBkYXRlOmNsaWVudHMgZGVsZXRlOmNsaWVudHMgY3JlYXRlOmNsaWVudHMgcmVhZDpjbGllbnRfa2V5cyB1cGRhdGU6Y2xpZW50X2tleXMgZGVsZXRlOmNsaWVudF9rZXlzIGNyZWF0ZTpjbGllbnRfa2V5cyByZWFkOmNvbm5lY3Rpb25zIHVwZGF0ZTpjb25uZWN0aW9ucyBkZWxldGU6Y29ubmVjdGlvbnMgY3JlYXRlOmNvbm5lY3Rpb25zIHJlYWQ6cmVzb3VyY2Vfc2VydmVycyB1cGRhdGU6cmVzb3VyY2Vfc2VydmVycyBkZWxldGU6cmVzb3VyY2Vfc2VydmVycyBjcmVhdGU6cmVzb3VyY2Vfc2VydmVycyByZWFkOmRldmljZV9jcmVkZW50aWFscyB1cGRhdGU6ZGV2aWNlX2NyZWRlbnRpYWxzIGRlbGV0ZTpkZXZpY2VfY3JlZGVudGlhbHMgY3JlYXRlOmRldmljZV9jcmVkZW50aWFscyByZWFkOnJ1bGVzIHVwZGF0ZTpydWxlcyBkZWxldGU6cnVsZXMgY3JlYXRlOnJ1bGVzIHJlYWQ6cnVsZXNfY29uZmlncyB1cGRhdGU6cnVsZXNfY29uZmlncyBkZWxldGU6cnVsZXNfY29uZmlncyByZWFkOmVtYWlsX3Byb3ZpZGVyIHVwZGF0ZTplbWFpbF9wcm92aWRlciBkZWxldGU6ZW1haWxfcHJvdmlkZXIgY3JlYXRlOmVtYWlsX3Byb3ZpZGVyIGJsYWNrbGlzdDp0b2tlbnMgcmVhZDpzdGF0cyByZWFkOnRlbmFudF9zZXR0aW5ncyB1cGRhdGU6dGVuYW50X3NldHRpbmdzIHJlYWQ6bG9ncyByZWFkOnNoaWVsZHMgY3JlYXRlOnNoaWVsZHMgZGVsZXRlOnNoaWVsZHMgdXBkYXRlOnRyaWdnZXJzIHJlYWQ6dHJpZ2dlcnMgcmVhZDpncmFudHMgZGVsZXRlOmdyYW50cyByZWFkOmd1YXJkaWFuX2ZhY3RvcnMgdXBkYXRlOmd1YXJkaWFuX2ZhY3RvcnMgcmVhZDpndWFyZGlhbl9lbnJvbGxtZW50cyBkZWxldGU6Z3VhcmRpYW5fZW5yb2xsbWVudHMgY3JlYXRlOmd1YXJkaWFuX2Vucm9sbG1lbnRfdGlja2V0cyByZWFkOnVzZXJfaWRwX3Rva2VucyBjcmVhdGU6cGFzc3dvcmRzX2NoZWNraW5nX2pvYiBkZWxldGU6cGFzc3dvcmRzX2NoZWNraW5nX2pvYiByZWFkOmN1c3RvbV9kb21haW5zIGRlbGV0ZTpjdXN0b21fZG9tYWlucyBjcmVhdGU6Y3VzdG9tX2RvbWFpbnMgcmVhZDplbWFpbF90ZW1wbGF0ZXMgY3JlYXRlOmVtYWlsX3RlbXBsYXRlcyB1cGRhdGU6ZW1haWxfdGVtcGxhdGVzIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.lF4y6bsYSFDPuZxLKTIMLQ2JApO_8wHc7L1hUblnMMN0OVJp7fwD9P6ek8C4nEfFRCK8-2qf3nFX98Vh51H4MJfgvsf45rRz9gtJtftbAis343fv2eTbx874WOJaZPDsiwaA7pHiHZg96KCYiT12dp87VAmVY3a8HLa6DxjngJbk2EcHIU2dXS7d8OA31UlTaAdz_SJ9k_gwcMySuAVD6w8se1AM50P6EMBXrhT5Honr9LJBDlb8Z67l6MmwobYaMe1W8AwtLpIpUaWQ20GtIyurNqXnn3wj6VgFSnsM9vG2Uf3rYXu-gf47LiPklyF2cIjyRl7ILbjAN4sAqna66Q")
                    .asString();

            resultArr = new ObjectMapper().readValue(response.getBody(), HashMap[].class);
            HashMap result = resultArr[0];
            System.out.println(result);

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

    @GetMapping("/product")
    public String displayOneProduct(Model model, @RequestParam("id") String id){
        try {
            JSONObject productJSON = jsonReader.getJson("http://product-wishlist-byteme.herokuapp.com/product" + id);
            Product product = new Product(String.valueOf(id),
                    productJSON.getBoolean("is_active"),
                    productJSON.getBoolean("is_incart"),
                    productJSON.getInt("price"),
                    productJSON.getInt("user_id"),
                    productJSON.getString("name"),
                    productJSON.getString("img"),
                    productJSON.getString("descr"));

            model.addAttribute("product", product);
            return "product_view";
        } catch (JSONException ex){
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/add")
    public String showNewProductForm(){ return "product_form"; }

    @PostMapping("/add")
    public String addNewProduct(@RequestBody MultiValueMap<String, String> formData){

        try {
            Map newForm = formData.toSingleValueMap();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(newForm, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity( "https://product-wishlist-byteme.herokuapp.com/product", request , String.class );
            System.out.println(newForm.toString());
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
