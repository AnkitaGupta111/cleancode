package com.kata.kata1.discounts.impl;

import com.kata.kata1.discounts.Price;
import com.kata.kata1.pojos.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OfferPrice implements Price {
    private final int numberOfProducts;
    private final double reducedPrice;

    @Override
    public double calculateProductPrice(Product product, int quantity) {
        return (quantity / numberOfProducts) * reducedPrice + (quantity % numberOfProducts) * product.getPrice();
    }
}
