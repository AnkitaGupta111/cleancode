package com.kata01.order;

import com.kata01.item.Item;
import java.util.Set;

public class GroceryOrder extends Order {

  @Override
  public void checkout(Set<Item> items) {
    getStrategyEngine().addDiscountStrategy(this);
  }

  @Override
  public float calculateFinalPrice(Set<Item> items) {
    return items.stream().map(GroceryOrder::calculateItemPrice).reduce(0f, Float::sum);
  }

  private static float calculateItemPrice(Item item) {
    return item.getDiscountStrategy().priceAfterDiscount(item);
  }
}
