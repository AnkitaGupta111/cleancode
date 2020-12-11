package com.kata01.discount;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;

public class DirectCashDiscountStrategy implements DiscountStrategy{
  @Override
  public void setPriceAfterDiscount(Item item, ItemOffer offer) {
    item.setCartPrice((item.getPrice() * item.getCartQuantity()) - (offer.getDiscountAmount() * item.getCartQuantity()));
  }
}
