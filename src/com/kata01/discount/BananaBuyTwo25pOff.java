package com.kata01.discount;

import com.kata01.item.Item;

public class BananaBuyTwo25pOff implements DiscountStrategy {


  @Override
  public float priceAfterDiscount(Item item) {
    float offerPrice;
    if (item.getCartQuantity() % 2 == 0) {
      return calculateBy2Get1Offer(item.getPrice(), item.getCartQuantity());
    } else {
      int offerSets = item.getCartQuantity() / 2;
      int leftOver = item.getCartQuantity() % 2;
      offerPrice = calculateBy2Get1Offer(item.getPrice(), offerSets);
      return offerPrice + leftOver * item.getPrice();
    }
  }

  private float calculateBy2Get1Offer(float itemPrice, int quantity) {
    float totalCartPrice = itemPrice * quantity;
    return totalCartPrice - (totalCartPrice * 25f) / 100;
  }
}
