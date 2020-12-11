package com.zemoso.code_clean.kata1.product;

public interface ICheckout {
    void scan(String item);
    Double total();
    String remove(String item);
}
