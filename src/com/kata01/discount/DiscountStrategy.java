package com.kata01.discount;

import com.kata01.item.Item;

public interface DiscountStrategy {

  float priceAfterDiscount(Item item);
}
