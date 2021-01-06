package com.kata.kata1.offers;

import com.kata.kata1.Item;

public class NoDiscount implements IOffer {

    /**
     * calculate price when there is no offer on items
     * @param item  whose price to be calculated
     * @param itemQuantity quantity of item scanned
     * @return total price
     */
    @Override
    public double calculatePrice(Item item, int itemQuantity) {
        return  itemQuantity*item.getPrice();
    }
}
