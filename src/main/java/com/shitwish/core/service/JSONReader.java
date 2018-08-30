package com.shitwish.core.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


@Component
public class JSONReader {
    private String getResponse(String url) {
        try {
            URL websiteURL = new URL(url);
            URLConnection con = websiteURL.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            inputLine = in.readLine();
            in.close();

            return inputLine;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JSONObject getJson(String url) {
        try {
            return new JSONObject(getResponse(url));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

