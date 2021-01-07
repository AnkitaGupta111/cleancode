package com.shopping.models;

/**
 * This class has the properties of an Item into a cart
 */
public class Product {
    private final String name;
    private final float unitPrice;
    private final int quantity;

    public Product(final String name, final float unitPrice, final int quantity) {
        this.unitPrice = unitPrice;
        this.name = name;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
