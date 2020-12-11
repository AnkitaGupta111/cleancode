package com.zemoso.code_clean.kata1.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zemoso.code_clean.kata1.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoadMasterData {
    public static Map<String, Product> pricingRules = new HashMap<>();

    @Bean
    public void load() {
        //REST API call to get the master data
        String json = "{\"A\":{\"price\":50,\"discount\":[{\"quantity\":3,\"price\":130}]},\"B\":{\"price\":30,\"discount\":[{\"quantity\":2,\"price\":45}]},\"C\":{\"price\":20,\"discount\":[]},\"D\":{\"price\":15,\"discount\":[]}}";
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Product>>() {
        }.getType();
        pricingRules = gson.fromJson(json, type);
        System.out.println("Pricing Rules :" + pricingRules);
    }
}
