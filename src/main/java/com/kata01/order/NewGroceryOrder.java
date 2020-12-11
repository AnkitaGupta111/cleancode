package com.kata01.order;


import com.kata01.model.Item;
import java.util.NoSuchElementException;
import java.util.Set;

public class NewGroceryOrder extends Order {
  @Override
  public void checkout(Set<Item> items) {
    this.setFinalPrice(items.stream().map(Item::getCartPrice).reduce(0f, Float::sum));
  }

  @Override
  public float calculateFinalPrice(Set<Item> items) {
    return 0;
  }

  public void addItem(Item item) {
    Integer currentCartQuantity = getItems().stream().filter(currentItem -> currentItem.getId() == item.getId())
        .map(Item::getCartQuantity).findFirst().orElse(0);
    item.setCartQuantity(currentCartQuantity + 1);
    getRuleEngine().calculateItemPrice(item);
    getItems().remove(item);
    getItems().add(item);
  }

  public void removeItem(Item item) {
    Integer currentCartQuantity = getItems().stream().filter(currentItem -> currentItem.getId() == item.getId()).findFirst()
        .map(Item::getCartQuantity).orElseThrow(() -> new NoSuchElementException("No such Item in the cart"));
    if (currentCartQuantity > 1) {
      item.setCartQuantity(currentCartQuantity - 1);
      getRuleEngine().calculateItemPrice(item);
      getItems().remove(item);
      getItems().add(item);
    } else {
      getItems().remove(item);
    }
  }
}
