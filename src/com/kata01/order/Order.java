package com.kata01.order;

import com.kata01.discount.DiscountStrategy;
import com.kata01.engine.StrategyEngine;
import com.kata01.item.Item;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Order {

  private Set<Item> items = new TreeSet<>();
  private float totalPrice;
  private float discountPrice;
  private float finalPrice = 0f;
  private DiscountStrategy discountStrategy = actualPrice -> 0;
  private StrategyEngine strategyEngine;

  public void addItem(Item item) {
    Integer currentCartQuantity = items.stream().filter(currentItem -> currentItem.getId() == item.getId())
        .map(Item::getCartQuantity).findFirst().orElse(0);
    item.setCartQuantity(currentCartQuantity + 1);
    items.remove(item);
    items.add(item);
  }

  public void removeItem(Item item) {
    Integer currentCartQuantity = items.stream().filter(currentItem -> currentItem.getId() == item.getId()).findFirst()
        .map(Item::getCartQuantity).orElseThrow(() -> new NoSuchElementException("No such Item in the cart"));
    if (currentCartQuantity > 1) {
      item.setCartQuantity(currentCartQuantity - 1);
      items.remove(item);
      items.add(item);
    } else {
      items.remove(item);
    }
  }


  public abstract void checkout(Set<Item> items);

  public abstract float calculateFinalPrice(Set<Item> items);
}
