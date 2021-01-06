package com.cleancode.shoppingcart;

import com.cleancode.shoppingcart.offers.CombinationOffer;
import com.cleancode.shoppingcart.offers.DiscountOffer;
import org.junit.Assert;
import org.junit.Test;

public class CartTest {
    @Test
    public void testAddOneItemTOBasket() {
        Cart cart = new Cart();

        ImmutableItem immutableItem = ImmutableItem.builder()
                .name("A")
                .quantity(4)
                .unitCost(5.0)
                .build();
        cart.addItemsToBasket(immutableItem);
        Assert.assertEquals(1, cart.getBasketSize());
    }

    @Test
    public void testGetThirtyPercentDiscountOffer() {
        Cart cart = new Cart();
        ImmutableItem immutableItem = ImmutableItem.builder()
                .name("A")
                .quantity(4)
                .unitCost(5.0)
                .build();
        cart.addItemsToBasket(immutableItem);

        DiscountOffer discountOffer = new DiscountOffer(30);
        cart.applyOfferToBasket(discountOffer, "A");

        double expectedTotal = 6;
        Assert.assertEquals(expectedTotal, cart.getTotal(),0.001);
    }

    @Test
    public void testGetTwoItemsForOneDollarOffer() {
        Cart cart = new Cart();
        ImmutableItem immutableItem = ImmutableItem.builder()
                .name("A")
                .quantity(4)
                .unitCost(5.0)
                .build();
        cart.addItemsToBasket(immutableItem);
        CombinationOffer combinationOffer = new CombinationOffer(1,2);
        cart.applyOfferToBasket(combinationOffer, "A");

        double expectedTotal = 2;
        Assert.assertEquals(expectedTotal, cart.getTotal(),0.001);
    }
}
