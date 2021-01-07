package com.kata.kata1.discounts.impl;

import com.kata.kata1.discounts.Price;
import com.kata.kata1.pojos.Product;

public class NoOfferPrice implements Price {
    @Override
    public double calculateProductPrice(Product product, int quantity) {
        return product.getPrice() * quantity;
    }
}
