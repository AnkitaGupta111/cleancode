package com.cleancode.shoppingcart.offers;

import com.cleancode.shoppingcart.ImmutableItem;

/**
 * DiscountOffer class deals with the discounts available on the products
 */
public class DiscountOffer implements IOffer {

    private final double percentage;

    public DiscountOffer(final double percentage) {
        this.percentage = percentage;
    }


    @Override
    public ImmutableItem apply(ImmutableItem item) {
        assert item != null : "item cannot be null";
        if (percentage < 0 || percentage > 100 ) {
            throw new IllegalArgumentException("Discount percentage must be in between 0 and 100");
        }

        return item.withCalculatedCost(item.getCalculatedCost()*percentage/100);
    }
}
