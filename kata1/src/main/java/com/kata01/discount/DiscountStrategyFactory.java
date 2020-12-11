package com.kata01.discount;

import com.kata01.dto.ItemOffer;

public class DiscountStrategyFactory {

  public DiscountStrategy getDiscountStrategy(ItemOffer offer) {
    DiscountStrategy strategy = new NoDiscountStrategy();
    switch (offer.getDiscountType()) {
      case DIRECT_PERCENTAGE:
        strategy = new DirectPercentageStrategy();
        break;
      case CASH_ON_QUANTITY:
        strategy = new CashDiscountOnQuantityStrategy();
        break;
      case PERCENTAGE_ON_QUANTITY:
        strategy = new PercentageOnQuantityStrategy();
        break;
      case DIRECT_CASH:
        strategy = new DirectCashDiscountStrategy();
        break;
      case NO_OFFER:
        strategy = new NoDiscountStrategy();
    }
    return strategy;
  }
}
