package com.zemoso.code_clean.kata1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Discount {
    private Integer quantity;
    private Double price;

    @Override
    public String toString() {
        return "{" +
                "quantity:" + quantity +
                ", price:" + price +
                '}';
    }
}
