package com.kata.kata1.offers;

import com.kata.kata1.Item;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class PriceDropOffer implements IOffer {

    int offerUnit;
    double offerPrice;

    /**
     * calculate price when there is special price rule applied for combination of items bought
     *
     * @param item         whose price to be calculated
     * @param itemQuantity quantity of same item scanned
     * @return total price based on special price rule
     */

    @Override
    public double calculatePrice(Item item, int itemQuantity) {
        double specialPrice = (itemQuantity / offerUnit) * offerPrice + (itemQuantity % offerUnit) * item.getPrice();
        return specialPrice;
    }
}
