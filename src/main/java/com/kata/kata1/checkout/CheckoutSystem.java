package com.kata.kata1.checkout;

import com.kata.kata1.Item;
import com.kata.kata1.offers.IOffer;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSystem implements ICheckoutSystem {
    Map<Item,Integer> cart=new HashMap<>();
    Map<Item, IOffer> priceRules;

    public CheckoutSystem(Map priceRules){
        this.priceRules=priceRules;
    }

    @Override
    public void scanItem(Item item) {
        if (cart.containsKey(item)) {
            int currQuantity=cart.get(item) + 1;
            cart.put(item,currQuantity);
        } else {
            cart.put(item, 1);
        }
    }

    @Override
    public float totalPrice() {
        float total = 0;

        for (Map.Entry mapEntry : cart.entrySet()) {
            Item item = (Item) mapEntry.getKey();
            int quantity = (int) mapEntry.getValue();
            if (priceRules.containsKey(item)) {
                IOffer priceRule = priceRules.get(item);
                total = total + priceRule.calculatePrice(item, quantity);
            }
        }

        return total;
    }

    @Override
    public void emptyCart() {
        cart.clear();
    }
}
