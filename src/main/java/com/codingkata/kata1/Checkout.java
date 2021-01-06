package com.codingkata.kata1;

public interface Checkout {
    void scanItem(String item);
    void scanRules(String rule);
    double total();
}
