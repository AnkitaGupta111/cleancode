package com.kata.kata1.services.impl;

import com.kata.kata1.discounts.Price;
import com.kata.kata1.pojos.Product;
import com.kata.kata1.services.PriceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PriceCalculatorImpl implements PriceCalculator {

    Map<Product, Integer> productList = new HashMap<>();
    Map<Product, Price> productPriceMap;

    public PriceCalculatorImpl(Map<Product, Price> productPriceMap) {
        this.productPriceMap = productPriceMap;
    }

    @Override
    public double calculatePrice() {
        double completePrice = 0;
        for (Map.Entry productEntry : productList.entrySet()) {
            Product product = (Product) productEntry.getKey();
            int quantity = (int) productEntry.getValue();
            if (productPriceMap.containsKey(product)) {
                Price price = productPriceMap.get(product);
                completePrice += price.calculateProductPrice(product, quantity);
            }
        }
        return completePrice;
    }

    @Override
    public void addProduct(Product product) {
        int productSize = productList.containsKey(product) ? productList.get(product) + 1 : 1;
        productList.put(product, productSize);
    }

    @Override
    public void addProductList(List<Product> productList) {
        productList.forEach(this::addProduct);
    }
}
