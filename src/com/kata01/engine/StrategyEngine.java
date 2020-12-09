package com.kata01.engine;

import com.kata01.order.Order;
import java.lang.reflect.InvocationTargetException;

public interface StrategyEngine {

  void addDiscountStrategy(Order order);
}
