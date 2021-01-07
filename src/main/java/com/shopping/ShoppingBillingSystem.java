package com.shopping;

import com.shopping.models.Product;
import com.shopping.models.SpecialPriceOffer;
import com.shopping.offer.OfferImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles adding item in the cart and calculation price for the items
 */
public class ShoppingBillingSystem {

    private final List<Product> items = new ArrayList<Product>();
    private final List<SpecialPriceOffer> offers = new ArrayList<SpecialPriceOffer>();

    public void addOffer(final SpecialPriceOffer specialPriceOffer) {
            offers.add(specialPriceOffer);
    }

    public List<SpecialPriceOffer> getOffers() {
        return offers;
    }

    public void addItem(@NotNull final Product product) {
        items.add(product);
    }

    public final List<Product> getItems() {
        return items;
    }

    /**
     * @param products the items in the cart
     * @param offers the offers for all item
     * @return the total price of the items in the cart
     */
    public float getTotalPrice(@NotNull final List<Product> products, final List<SpecialPriceOffer> offers) {
        float totalPrice = 0;
        OfferImpl offerImpl = new OfferImpl();
        for(final Product product : products) {
             SpecialPriceOffer specialPriceOffer = offers.stream().
                     filter(offer -> offer.getOfferName().equals(product.getName()))
                     .findAny()
                     .orElse(null);
                totalPrice = totalPrice + offerImpl.getPriceAfterOfferApplied(product, specialPriceOffer);
        }
        return totalPrice;
    }
}
