package com.codingkata.kata1;

/**
 * Interface for scanning items ,rules and calculate the total price of items
 */
public interface Checkout {
    /**
     *
     * @param item scans new item into cart
     */
    void scanItem(String item);

    /**
     *
     * @param rule scans rules
     */
    void scanRules(String rule);

    /**
     * calculates total price
     * @return total bill
     */
    double calculateTotalPrice();
}
