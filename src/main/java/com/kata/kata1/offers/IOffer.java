package com.kata.kata1.offers;

import com.kata.kata1.Item;

public interface IOffer {
   Float calculatePrice(Item item,int itemQuantity);
}
