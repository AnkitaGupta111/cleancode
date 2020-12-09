package com.kata01.discount;

import com.kata01.item.Item;

public class NoDiscount implements DiscountStrategy {
  @Override
  public float priceAfterDiscount(Item item) {
    return item.getPrice() * item.getCartQuantity();
  }
}
