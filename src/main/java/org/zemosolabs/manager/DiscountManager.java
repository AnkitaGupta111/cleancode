package org.zemosolabs.manager;

import org.zemosolabs.basket.Basket;

public interface DiscountManager {
    double price(Basket basket);
}
