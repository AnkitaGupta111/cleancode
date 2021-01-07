package com.katas.kata1.approach1.Price;

/**
 * declares method required to calculate price of similar items
 */
interface IPriceCalculation {
    /**
     * calculates total price with discount if any
     *
     * @param itemPrice    price of item
     * @param itemQuantity items count
     * @return total price
     */
    double calculateTotalPrice(double itemPrice, int itemQuantity);

}
