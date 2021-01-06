package com.kata.kata1.offers;

import com.kata.kata1.Item;

/*
calculate total price of same items according to offer class implemented
 */
public interface IOffer {
    double calculatePrice(Item item, int itemQuantity);
}
