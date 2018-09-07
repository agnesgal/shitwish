package com.shitwish.core.service;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class Parser {

    private Gson gson;

    public Parser(Gson gson) {
        this.gson = gson;
    }

    private String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

    public <T> T readJsonFromUrl(String url, Class<T> klass) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            return gson.fromJson(jsonText, klass);
        }
        finally {
            inputStream.close();
        }
    }

    public <T> T readJsonFromUrlSplitted(String url, Class<T> klass) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            jsonText = jsonText.split(":", 2)[1];
            jsonText = jsonText.substring(0, jsonText.length() - 1);
            return gson.fromJson(jsonText, klass);
        }
        finally {
            inputStream.close();
        }
    }
}
