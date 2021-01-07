package com.shopping.offer;

import com.shopping.models.Product;
import org.jetbrains.annotations.NotNull;

public class SpecialPriceOffer implements IOffer{
    private String offerName;
    private int offerQuantity;
    private float specialPrice;

    public SpecialPriceOffer( final String offerName, final int offerQuantity, float specialPrice) {
        this.offerName = offerName;
        this.offerQuantity = offerQuantity;
        this.specialPrice = specialPrice;
    }

    public float getPriceAfterOfferApplied(@NotNull Product product) {
        if(product.getName().equals(offerName))
            return (product.getQuantity() / offerQuantity) * specialPrice +
                (product.getQuantity() % offerQuantity) * product.getUnitPrice();
        return (product.getQuantity() * product.getUnitPrice());
    }
}
