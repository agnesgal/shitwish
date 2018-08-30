package com.shitwish.core;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShitwishApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShitwishApplication.class, args);
    }

    @Bean
    public Gson provideGson() {
        return new Gson();
    }
}
