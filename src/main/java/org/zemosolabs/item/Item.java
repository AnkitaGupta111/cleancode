package org.zemosolabs.item;

import org.zemosolabs.manager.DiscountManager;

public class Item implements Comparable<Item> {

    private double id;
    private String name;
    private double originalPrice;
    private double discountedPrice;
    private double discount;
    private int quantity;
    private DiscountManager discountManager;

    public Item(String name, double originalPrice) {
        this.name = name;
        this.originalPrice = originalPrice;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DiscountManager getDiscountManager() {
        return discountManager;
    }

    public void setDiscountManager(DiscountManager discountManager) {
        this.discountManager = discountManager;
    }

    @Override
    public int compareTo(Item item) {
        return (int) (this.id-item.id);
    }
}
