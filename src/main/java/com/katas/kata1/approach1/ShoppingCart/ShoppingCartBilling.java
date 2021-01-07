package com.katas.kata1.approach1.ShoppingCart;

import com.katas.kata1.approach1.Item;
import com.katas.kata1.approach1.Price.PriceCalculation;
import com.katas.kata1.approach1.PriceRule;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartBilling implements IShoppingCartBilling {
    Map<Item, Integer> shoppingCart = new HashMap<>();
    Map<Item, PriceRule> priceRules;

    public ShoppingCartBilling(Map priceRules) {
        this.priceRules = priceRules;
    }

    /**
     * adds item to the cart
     *
     * @param item item to be added to the cart
     */

    @Override
    public void addItem(Item item) {
        if (shoppingCart.containsKey(item)) {
            int currQuantity = shoppingCart.get(item) + 1;
            shoppingCart.put(item, currQuantity);
        } else {
            shoppingCart.put(item, 1);
        }
    }

    /**
     * calculates total bill
     *
     * @return total bill of the items in cart
     */
    @Override
    public double billing() {
        double total = 0;
        for (Map.Entry cartEntry : shoppingCart.entrySet()) {
            Item item = (Item) cartEntry.getKey();
            int quantity = (int) cartEntry.getValue();
            if (priceRules.containsKey(item)) {
                PriceRule priceRule = priceRules.get(item);
                total = total + new PriceCalculation(priceRule).calculateTotalPrice(item.getPrice(), quantity);
            }
        }
        return total;
    }

    /**
     * removes all items in the cart
     */
    public void clearCart() {
        shoppingCart.clear();
    }

}
