package com.kata01.order;

import com.kata01.discount.DiscountStrategy;
import com.kata01.engine.RuleEngine;
import com.kata01.engine.StrategyEngine;
import com.kata01.model.Item;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Order {

  private Set<Item> items = new TreeSet<>();
  private float totalPrice;
  private float discountPrice;
  private float finalPrice = 0f;
  private DiscountStrategy discountStrategy;
  private StrategyEngine strategyEngine;
  private RuleEngine ruleEngine;

  public abstract void addItem(Item item);

  public abstract void removeItem(Item item);

  public abstract void checkout(Set<Item> items);

  public abstract float calculateFinalPrice(Set<Item> items);
}
