package com.kata01.discount;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;

public class NoDiscountStrategy implements DiscountStrategy {
  @Override
  public void setPriceAfterDiscount(Item item, ItemOffer offer) {
    item.setCartPrice(item.getCartQuantity() + item.getPrice());
  }
}
