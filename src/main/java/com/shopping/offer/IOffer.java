package com.shopping.offer;

import com.shopping.models.Product;
import com.shopping.models.SpecialPriceOffer;
import org.jetbrains.annotations.NotNull;

/**
 * This interface includes getPriceAfterOfferApplied method which return total price of an product with offer
 */
public interface IOffer {
    float getPriceAfterOfferApplied(@NotNull final Product product, final SpecialPriceOffer offer);
}
