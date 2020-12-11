package com.kata01.engine;

import com.kata01.order.Order;

public interface StrategyEngine {

  void addDiscountStrategy(Order order);
}
