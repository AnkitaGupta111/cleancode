package com.kata.kata1.checkout;

import com.kata.kata1.Item;

/**
 * provide api for scan items and add to cart ,empty cart and calculate total bill
 */
interface ICheckoutSystem {

    /**
     * add item to cart
     *
     * @param item to add in cart
     */
    void scanItem(Item item);

    /**
     * calculate total bill
     *
     * @return total bill of the items in cart
     */
    double totalPrice();

    /**
     * remove all items in cart
     */
    void emptyCart();
}
