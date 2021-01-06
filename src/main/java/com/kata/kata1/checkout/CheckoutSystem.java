package com.kata.kata1.checkout;

import com.kata.kata1.Item;
import com.kata.kata1.offers.IOffer;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSystem implements ICheckoutSystem {
    Map<Item, Integer> cart = new HashMap<>();
    Map<Item, IOffer> priceRules;

    public CheckoutSystem(Map priceRules) {
        this.priceRules = priceRules;
    }

    /**
     * add item to cart
     *
     * @param item scanned
     */

    @Override
    public void scanItem(Item item) {
        if (cart.containsKey(item)) {
            int currQuantity = cart.get(item) + 1;
            cart.put(item, currQuantity);
        } else {
            cart.put(item, 1);
        }
    }

    /**
     * calculate total price of the items in cart
     *
     * @return total price
     */
    @Override
    public double totalPrice() {
        double total = 0;

        for (Map.Entry cartEntry : cart.entrySet()) {
            Item item = (Item) cartEntry.getKey();
            int quantity = (int) cartEntry.getValue();
            if (priceRules.containsKey(item)) {
                IOffer priceRule = priceRules.get(item);
                total = total + priceRule.calculatePrice(item, quantity);
            }
        }

        return total;
    }

    /**
     * remove all the items from cart
     */
    @Override
    public void emptyCart() {
        cart.clear();
    }
}
