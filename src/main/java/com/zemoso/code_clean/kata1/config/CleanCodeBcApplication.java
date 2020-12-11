package com.zemoso.code_clean.kata1.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zemoso.code_clean.kata1.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.Map;

@SpringBootApplication
public class CleanCodeBcApplication {
    //consider the case of removing the item from cart
    //approach is more imp than imple
    //offers is the function of price and quantity

    public static void main(String[] args) {

        SpringApplication.run(CleanCodeBcApplication.class, args);
    }
}
