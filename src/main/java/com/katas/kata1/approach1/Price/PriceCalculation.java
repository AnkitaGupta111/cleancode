package com.katas.kata1.approach1.Price;

import com.katas.kata1.approach1.PriceRule;

public class PriceCalculation implements IPriceCalculation {
    private PriceRule priceRule;

    public PriceCalculation(PriceRule priceRule) {
        this.priceRule = priceRule;
    }

    /**
     * calculates total price with discount if any
     *
     * @param itemPrice    price of item
     * @param itemQuantity items count
     * @return total price
     */
    @Override
    public double calculateTotalPrice(double itemPrice, int itemQuantity) {
        double totalAmount;
        if (priceRule.getOfferPrice() != 0) {
            totalAmount = (itemQuantity / priceRule.getItemsWithOfferCount()) * priceRule.getOfferPrice() + (itemQuantity % priceRule.getItemsWithOfferCount()) * itemPrice;
        } else {
            totalAmount = itemPrice * itemQuantity;
        }
        return totalAmount;
    }
}
