package com.katas.kata1.approach1.ShoppingCart;

import com.katas.kata1.approach1.Item;

/**
 * declares methods ,which are needed to perform basic actions like adding item to the cart ,calculating total bill,clearing the cart
 */
public interface IShoppingCartBilling {

    /**
     * adds item to the cart
     *
     * @param item item to be added to the cart
     */
    void addItem(Item item);

    /**
     * calculates total bill
     *
     * @return total bill of the items in cart
     */
    double billing();

    /**
     * removes all items in the cart
     */
    void clearCart();
}
