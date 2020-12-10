package org.zemosolabs;

import org.junit.Assert;
import org.junit.Test;
import org.zemosolabs.basket.Basket;
import org.zemosolabs.item.Item;
import org.zemosolabs.manager.Buy2Get1;
import org.zemosolabs.manager.DiscountManager;

public class AppTest {
    @Test
    public void listBasketSize()
    {
        Item item = new Item("A", 10);
        Basket basket = new Basket();
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);
        Assert.assertEquals(5, basket.getItems().get(0).getQuantity());

        basket.removeItem(item);
        Assert.assertEquals(4, basket.getItems().get(0).getQuantity());
    }

    @Test
    public void Buy2Get1() {
        Item item = new Item("A", 10);
        Basket basket = new Basket();
        basket.addItem(item);
        basket.addItem(item);
        basket.addItem(item);

        DiscountManager discountManager = new Buy2Get1();
        double price = discountManager.price(basket);
        System.out.println(price);
    }
}

