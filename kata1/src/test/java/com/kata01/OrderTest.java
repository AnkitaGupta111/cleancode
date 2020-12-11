package com.kata01;

import com.kata01.engine.FridayStrategy;
import com.kata01.model.Item;
import com.kata01.order.GroceryOrder;
import com.kata01.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void test3Apple2Banana() {
    Order order = new GroceryOrder();
    order.setStrategyEngine(new FridayStrategy());
    Item apple = Item.builder().id(1).name("apple").price(50).build();
    Item banana = Item.builder().id(2).name("banana").price(30).build();
    order.addItem(apple);
    order.addItem(apple);
    order.addItem(apple);
    order.addItem(apple);
    order.removeItem(apple);
    order.addItem(banana);
    order.addItem(banana);
    order.checkout(order.getItems());
    float finalPrice = order.calculateFinalPrice(order.getItems());
    Assertions.assertEquals(175, finalPrice);
  }

  @Test
  void test1Apple1Banana() {
    Order order = new GroceryOrder();
    order.setStrategyEngine(new FridayStrategy());
    Item apple = Item.builder().id(1).name("apple").price(50).build();
    Item banana = Item.builder().id(2).name("banana").price(30).build();
    order.addItem(apple);
    order.addItem(banana);
    order.checkout(order.getItems());
    float finalPrice = order.calculateFinalPrice(order.getItems());
    Assertions.assertEquals(80, finalPrice);
  }

  @Test
  void testEmptyCart() {
    Order order = new GroceryOrder();
    order.setStrategyEngine(new FridayStrategy());
    Item apple = Item.builder().id(1).name("apple").price(50).build();
    Item banana = Item.builder().id(2).name("banana").price(30).build();
    order.addItem(apple);
    order.addItem(banana);
    order.removeItem(apple);
    order.removeItem(banana);
    order.checkout(order.getItems());
    //float finalPrice = order.calculateFinalPrice(order.getItems());
    Assertions.assertEquals(0, order.getFinalPrice());
  }
}
