package com.kata.kata1.offers;

import com.kata.kata1.Item;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class PriceDropOffer implements IOffer {

    int offerUnit;
    float offerPrice;

    @Override
    public Float calculatePrice(Item item,int itemQuantity) {
        float specialPrice= (itemQuantity/offerUnit)*offerPrice + (itemQuantity%offerUnit)*item.getPrice();
        return specialPrice;
    }
}
