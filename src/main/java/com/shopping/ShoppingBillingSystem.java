package com.shopping;

import com.shopping.models.Product;
import com.shopping.offer.IOffer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBillingSystem {

    private final List<Product> items = new ArrayList<Product>();

    public void addItem(@NotNull final Product product) {
        items.add(product);
    }

    public final List<Product> getItems() {
        return items;
    }

    public float getTotalPrice(@NotNull final List<Product> products, final IOffer offer) {
        float totalPrice = 0;
        for(Product product : products) {
                totalPrice = totalPrice + offer.getPriceAfterOfferApplied(product);
        }
        return totalPrice;
    }
}
