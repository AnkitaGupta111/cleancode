package com.kata01.engine;

import com.kata01.discount.DiscountStrategy;
import com.kata01.discount.NoDiscount;
import com.kata01.item.Item;
import com.kata01.order.Order;
import java.lang.reflect.InvocationTargetException;

public class FridayStrategy implements StrategyEngine {

  @Override
  public void addDiscountStrategy(Order order) {
    for (Item item : order.getItems()) {
      assignDiscountStrategyToItem(item);
    }
  }

  private void assignDiscountStrategyToItem(Item item) {
    //@TODO do a backend call to get all the discounts applied on this item
    DiscountStrategy discountStrategy = new NoDiscount();
    try {
      Class<?> clazz = null;
      if (item.getId() == 1)
        clazz = Class.forName("com.kata01.discount.AppleBuy2Get1FreeOffer");
      if (item.getId() == 2)
        clazz = Class.forName("com.kata01.discount.BananaBuyTwo25pOff");
      if (clazz != null) {
        discountStrategy = (DiscountStrategy) clazz.getDeclaredConstructor().newInstance();
      }
    } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    item.setDiscountStrategy(discountStrategy);
  }
}
