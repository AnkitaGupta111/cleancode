package com.kata01;

import com.kata01.engine.MyStoreRuleEngine;
import com.kata01.model.Item;
import com.kata01.order.NewGroceryOrder;
import com.kata01.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NewOrderTest {

  @Test
  void test3Apple2Banana() {
    Order order = new NewGroceryOrder();
    order.setRuleEngine(new MyStoreRuleEngine());
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
    float finalPrice = order.getFinalPrice();
    Assertions.assertEquals(175, finalPrice);
  }

  @Test
  void test1Apple1Banana() {
    Order order = new NewGroceryOrder();
    order.setRuleEngine(new MyStoreRuleEngine());
    Item apple = Item.builder().id(1).name("apple").price(50).build();
    Item banana = Item.builder().id(2).name("banana").price(30).build();
    order.addItem(apple);
    order.addItem(banana);
    order.checkout(order.getItems());
    float finalPrice = order.getFinalPrice();
    Assertions.assertEquals(80, finalPrice);
  }

  @Test
  void testEmptyCart() {
    Order order = new NewGroceryOrder();
    order.setRuleEngine(new MyStoreRuleEngine());
    Item apple = Item.builder().id(1).name("apple").price(50).build();
    Item banana = Item.builder().id(2).name("banana").price(30).build();
    order.addItem(apple);
    order.addItem(banana);
    order.removeItem(apple);
    order.removeItem(banana);
    order.checkout(order.getItems());
    float finalPrice = order.getFinalPrice();
    Assertions.assertEquals(0, finalPrice);
  }

  @Test
  void testOneEachItem() {
    Order order = new NewGroceryOrder();
    order.setRuleEngine(new MyStoreRuleEngine());
    Item apple = Item.builder().id(1).name("apple").price(50).build();
    Item banana = Item.builder().id(2).name("banana").price(30).build();
    Item chocolate = Item.builder().id(3).name("DairyMilk").price(80).build();
    Item biscuit = Item.builder().id(4).name("biscuit").price(100).build();
    order.addItem(apple);
    order.addItem(banana);
    order.addItem(chocolate);
    order.addItem(biscuit);
    order.checkout(order.getItems());
    float finalPrice = order.getFinalPrice();
    Assertions.assertEquals(242, finalPrice);
  }
}
