package com.kata.kata1.checkout;

import com.kata.kata1.Item;

interface  ICheckoutSystem {
    void scanItem(Item item);
    double totalPrice();
    void emptyCart();
}
