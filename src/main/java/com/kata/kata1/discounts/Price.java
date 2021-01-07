package com.kata.kata1.discounts;

import com.kata.kata1.pojos.Product;

public interface Price {
    /**
     * Calculates price of product given the quantity and applies discount if any
     *
     * @param product  Product for which price is to be calculated
     * @param quantity quantity of product added
     * @return Total price of product for given quantity with discount applied
     */
    double calculateProductPrice(Product product, int quantity);
}
