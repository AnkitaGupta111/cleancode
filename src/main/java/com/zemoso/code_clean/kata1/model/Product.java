package com.zemoso.code_clean.kata1.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product {
    private Double price;
    private List<Discount> discount;

    @Override
    public String toString() {
        return "{" +
                "price:" + price +
                ", discount:" + discount +
                '}';
    }
}
