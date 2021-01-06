package com.kata.kata1;

import com.kata.kata1.checkout.CheckoutSystem;
import com.kata.kata1.offers.IOffer;
import com.kata.kata1.offers.NoDiscount;
import com.kata.kata1.offers.PriceDropOffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckOutSystemTest {

    Item item1 = new Item("A", 50);
    Item item2 = new Item("B", 30);
    Item item3 = new Item("C", 20);
    Item item4 = new Item("D", 15);


    Map<Item, IOffer> getRules() {
        Map<Item, IOffer> priceRules = new HashMap<>();
        priceRules.put(item1, new PriceDropOffer(3, 130));
        priceRules.put(item2, new PriceDropOffer(2, 45));
        priceRules.put(item3, new NoDiscount());
        priceRules.put(item4, new NoDiscount());
        return priceRules;
    }

    CheckoutSystem checkout = new CheckoutSystem(getRules());

    @AfterEach
    void clearCart() {
        checkout.emptyCart();
    }

    @Test
    void testTotalPriceWhenNoSpecialPriceApplied() {

        //cart->"AB"
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        Assertions.assertEquals(80, checkout.totalPrice());
        checkout.emptyCart();

        //cart->"CDBA"
        checkout.scanItem(item3);
        checkout.scanItem(item4);
        checkout.scanItem(item2);
        checkout.scanItem(item1);

        Assertions.assertEquals(115, checkout.totalPrice());

    }

    @Test
    void testEmptyCartPrice() {
        //cart->""
        Assertions.assertEquals(0, checkout.totalPrice());
    }

    @Test
    void testPriceWhenSpecialPriceApplied() {
        //cart->"AAAA"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);

        Assertions.assertEquals(180, checkout.totalPrice());
        checkout.emptyCart();

        //cart->"AAABB"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        checkout.scanItem(item2);

        Assertions.assertEquals(175, checkout.totalPrice());

    }

}