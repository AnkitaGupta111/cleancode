package com.katas.kata1;

import com.katas.kata1.approach1.Item;
import com.katas.kata1.approach1.PriceRule;
import com.katas.kata1.approach1.ShoppingCart.ShoppingCartBilling;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
class SuperMarketBillingApproach1Test {


    Item item1 = new Item("A", 50);
    Item item2 = new Item("B", 30);
    Item item3 = new Item("C", 20);
    Item item4 = new Item("D", 15);

    Map<Item, PriceRule> getPriceRules() {
        Map<Item, PriceRule> priceRules = new HashMap<>();
        priceRules.put(item1, new PriceRule(130, 3));
        priceRules.put(item2, new PriceRule(45, 2));
        priceRules.put(item3, new PriceRule(0, 0));
        priceRules.put(item4, new PriceRule(0, 0));
        return priceRules;
    }

    ShoppingCartBilling shoppingCart = new ShoppingCartBilling(getPriceRules());

    @AfterEach
    void clear() {
        shoppingCart.clearCart();
    }

    @Test
    void testBillingWhenCartIsEmpty() {
        Assertions.assertEquals(0, shoppingCart.billing());
    }

    @Test
    void testBillingWithItemsInCartCase1() {
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item1);
        Assertions.assertEquals(180, shoppingCart.billing());
    }

    @Test
    void testBillingWithItemsInCartCase2() {
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);
        shoppingCart.addItem(item3);
        shoppingCart.addItem(item4);
        Assertions.assertEquals(115, shoppingCart.billing());
    }

    @Test
    void testBillingWithItemsInCartCase3() {
        shoppingCart.addItem(item4);
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);
        shoppingCart.addItem(item1);
        Assertions.assertEquals(190, shoppingCart.billing());
    }

}