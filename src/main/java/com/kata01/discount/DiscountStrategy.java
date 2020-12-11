package com.kata01.discount;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;

public interface DiscountStrategy {

  void setPriceAfterDiscount(Item item, ItemOffer offer);
}
