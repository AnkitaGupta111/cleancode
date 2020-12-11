package com.kata01.discount;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;

public class BananaBuyTwo25pOff implements DiscountStrategy {


  @Override
  public void setPriceAfterDiscount(Item item, ItemOffer offer) {
    float offerPrice;
    if (item.getCartQuantity() % 2 == 0) {
      item.setCartPrice(calculateBy2Get1Offer(item.getPrice(), item.getCartQuantity()));
    } else {
      int offerSets = item.getCartQuantity() / 2;
      int leftOver = item.getCartQuantity() % 2;
      offerPrice = calculateBy2Get1Offer(item.getPrice(), offerSets);
      item.setCartPrice(offerPrice + leftOver * item.getPrice());
    }
  }

  private float calculateBy2Get1Offer(float itemPrice, int quantity) {
    float totalCartPrice = itemPrice * quantity;
    return totalCartPrice - (totalCartPrice * 25f) / 100;
  }
}
