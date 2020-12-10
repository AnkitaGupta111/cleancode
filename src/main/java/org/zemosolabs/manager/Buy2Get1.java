package org.zemosolabs.manager;

import org.zemosolabs.basket.Basket;
import org.zemosolabs.item.Item;

public class Buy2Get1 implements DiscountManager{

    @Override
    public double price(Basket basket) {
        double totalPrice = 0;
        // add some custom logic here
        return totalPrice;
    }

    public double calculatePrice(Item item) {
        // add some custom logic
        return 0;
    }
}
