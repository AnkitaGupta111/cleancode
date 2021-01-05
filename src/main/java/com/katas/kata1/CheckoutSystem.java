package com.katas.kata1;

import java.util.List;

/**
 * CheckoutSystem contains methods to take inputs of billing items and newRules of items
 * method to calculate the total billing of items
 */
public interface CheckoutSystem {
    /**
     * scans the items
     * @param items ,, billing items given as input
     */
    void scanInputs(String items);

    /**
     * scans the newRules
     * @param newRules ,, rules in the form of Strings where itemName, unitPrice, offerItems, offerPrice are separated by hyphens
     */
    void scanNewRules(List<String> newRules);

    /**
     * calculates the total billing
     * @return total amount of billing items
     */
    double calculateBillingOfItems();
}
