package com.kata01.discount;

import com.kata01.item.Item;

public class AppleBuy2Get1FreeOffer implements DiscountStrategy {
  @Override
  public float priceAfterDiscount(Item item) {
    float offerPrice;
    if (item.getCartQuantity() % 3 == 0) {
      return calculateBy2Get1Offer(item.getPrice(), item.getCartQuantity());
    } else {
      int offerSets = item.getCartQuantity() / 3;
      int leftOver = item.getCartQuantity() % 3;
      offerPrice = calculateBy2Get1Offer(item.getPrice(), offerSets);
      return offerPrice + leftOver * item.getPrice();
    }
  }

  private float calculateBy2Get1Offer(float itemPrice, int quantity) {
    return 130f * (quantity / 3);
  }
}
