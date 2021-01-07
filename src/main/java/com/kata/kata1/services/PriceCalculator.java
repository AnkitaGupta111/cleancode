package com.kata.kata1.services;

import com.kata.kata1.pojos.Product;

import java.util.List;

public interface PriceCalculator {
    /**
     * Calculates price of previously added items
     *
     * @return Total price of products with discounts if any.
     */
    double calculatePrice();

    /**
     * @param product Product that is added before calculatePrice is called
     */
    void addProduct(Product product);

    /**
     * @param productList List of products added before calculatePrice is called
     */
    void addProductList(List<Product> productList);
}
