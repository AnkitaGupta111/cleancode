package com.shopping.offer;

import com.shopping.models.Product;
import com.shopping.models.SpecialPriceOffer;
import org.jetbrains.annotations.NotNull;

public class OfferImpl implements IOffer {

    public OfferImpl() {}

    @Override
    public float getPriceAfterOfferApplied(@NotNull Product product, final SpecialPriceOffer offer) {
        if(offer != null)
            return (product.getQuantity() / offer.getOfferQuantity()) * offer.getSpecialPrice() +
                    (product.getQuantity() % offer.getOfferQuantity()) * product.getUnitPrice();
        return (product.getQuantity() * product.getUnitPrice());
    }
}
