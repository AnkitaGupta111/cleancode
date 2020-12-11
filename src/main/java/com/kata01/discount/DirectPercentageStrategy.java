package com.kata01.discount;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;

public class DirectPercentageStrategy implements DiscountStrategy {

  @Override
  public void setPriceAfterDiscount(Item item, ItemOffer offer) {
    float finalPrice = ((item.getPrice() * item.getCartQuantity()) - ((item.getPrice() * item.getCartQuantity()) * offer.getDiscountPercentage()) / 100);
    item.setCartPrice(finalPrice);
  }
}
