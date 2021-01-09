package com.kata.kata1.services;

import com.kata.kata1.pojos.Product;

import java.util.Map;

public interface PriceCalculator {

    double calculatePrice(Map<Product, Integer> productList);
}
