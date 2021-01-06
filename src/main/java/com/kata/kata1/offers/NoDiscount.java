package com.kata.kata1.offers;

import com.kata.kata1.Item;

public class NoDiscount implements IOffer {

    @Override
    public Float calculatePrice(Item item, int itemQuantity) {
        return  itemQuantity*item.getPrice();
    }
}
