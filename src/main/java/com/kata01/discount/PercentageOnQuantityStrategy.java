package com.kata01.discount;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;

public class PercentageOnQuantityStrategy implements DiscountStrategy {
  @Override
  public void setPriceAfterDiscount(Item item, ItemOffer offer) {
    float finalPrice;
    int offerSets = item.getCartQuantity() / offer.getQuantity();
    int leftOver = item.getCartQuantity() % offer.getQuantity();
    if (leftOver == 0) {
      finalPrice = ((item.getPrice() * item.getCartQuantity()) - ((item.getPrice() * item.getCartQuantity()) * (offer.getDiscountPercentage())) / 100);
    } else {
      finalPrice = ((item.getPrice() * item.getCartQuantity()) - ((item.getPrice() * offerSets * offer.getQuantity()) * (offer.getDiscountPercentage())) / 100);
    }
    item.setCartPrice(finalPrice);
  }
}
