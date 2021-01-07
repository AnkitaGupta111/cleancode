package com.shopping.offer;

import com.shopping.models.Product;
import org.jetbrains.annotations.NotNull;

public interface IOffer {
    float getPriceAfterOfferApplied(@NotNull final Product product);
}
