package com.cleancode.shoppingcart;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Item {
    public abstract String getName();

    public abstract int getQuantity();

    public abstract double getUnitCost();

    @Value.Default
    public double getCalculatedCost() {
        return 1;
    };
}
