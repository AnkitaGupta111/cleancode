package com.cleancode.shoppingcart.offers;

import com.cleancode.shoppingcart.ImmutableItem;

/**
 * This class deals with the offers of the type 'buy two pens for 1$'
 */
public class CombinationOffer implements IOffer {

    private final int offerPrice;

    private final int offerQuantity;

    public CombinationOffer(final int offerPrice, final int offerQuantity) {
        this.offerPrice = offerPrice;
        this.offerQuantity = offerQuantity;
    }

    @Override
    public ImmutableItem apply(final ImmutableItem item) {
        assert item != null : "item cannot be null";
        int basketQuantity = item.getQuantity();
        int productCombinationCount = basketQuantity / offerQuantity;
        int remainingProducts = basketQuantity % offerQuantity;
        double priceAfterOffer = (productCombinationCount * offerPrice) + (remainingProducts * item.getUnitCost());
        return item.withCalculatedCost(priceAfterOffer);
    }
}
