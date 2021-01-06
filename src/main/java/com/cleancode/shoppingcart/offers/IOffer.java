package com.cleancode.shoppingcart.offers;

import com.cleancode.shoppingcart.ImmutableItem;

public interface IOffer {
    ImmutableItem apply(ImmutableItem item);
}