package com.kata01.order;

import com.kata01.model.Item;
import java.util.NoSuchElementException;
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

  public void addItem(Item item) {
    Integer currentCartQuantity = getItems().stream().filter(currentItem -> currentItem.getId() == item.getId())
        .map(Item::getCartQuantity).findFirst().orElse(0);
    item.setCartQuantity(currentCartQuantity + 1);
    getItems().remove(item);
    getItems().add(item);
  }

  public void removeItem(Item item) {
    Integer currentCartQuantity = getItems().stream().filter(currentItem -> currentItem.getId() == item.getId()).findFirst()
        .map(Item::getCartQuantity).orElseThrow(() -> new NoSuchElementException("No such Item in the cart"));
    if (currentCartQuantity > 1) {
      item.setCartQuantity(currentCartQuantity - 1);
      getItems().remove(item);
      getItems().add(item);
    } else {
      getItems().remove(item);
    }
  }

  private static float calculateItemPrice(Item item) {
    item.getDiscountStrategy().setPriceAfterDiscount(item, null);
    return item.getCartPrice();
  }
}
