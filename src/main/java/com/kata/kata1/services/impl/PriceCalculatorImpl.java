package com.kata.kata1.services.impl;

import com.kata.kata1.discounts.Price;
import com.kata.kata1.pojos.Product;
import com.kata.kata1.services.PriceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PriceCalculatorImpl implements PriceCalculator {

    Map<Product, Price> productPriceMap;

    public PriceCalculatorImpl(Map<Product, Price> productPriceMap) {
        this.productPriceMap = productPriceMap;
    }

    @Override
    public double calculatePrice(Map<Product, Integer> addedProductMap) {
        double completePrice = 0;
        for (Map.Entry productEntry : addedProductMap.entrySet()) {
            Product product = (Product) productEntry.getKey();
            Integer quantity = (Integer) productEntry.getValue();
            if (productPriceMap.containsKey(product)) {
                Price price = productPriceMap.get(product);
                completePrice += price.calculateProductPrice(product, quantity);
            }
        }
        return completePrice;
    }
}
