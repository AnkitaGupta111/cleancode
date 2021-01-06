package com.kata.kata1;

import com.kata.kata1.checkout.CheckoutSystem;
import com.kata.kata1.offers.IOffer;
import com.kata.kata1.offers.NoDiscount;
import com.kata.kata1.offers.PriceDropOffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckOutSystemTest {

    Item item1=new Item("A",50);
    Item item2=new Item("B",30);
    Item item3=new Item("C",20);
    Item item4=new Item("D",15);


    Map<Item, IOffer> getRules(){
        Map<Item, IOffer>  priceRules=new HashMap<>();
        priceRules.put(item1,new PriceDropOffer(3,130));
        priceRules.put(item2,new PriceDropOffer(2,45));
        priceRules.put(item3,new NoDiscount());
        priceRules.put(item4,new NoDiscount());
        return priceRules;
    }

    @Test
    void testTotalPrice() {

        CheckoutSystem checkout= new CheckoutSystem(getRules());
        //1.cart->""
        Assertions.assertEquals(0,checkout.totalPrice());
        checkout.emptyCart();
        //2.cart->"A"
        checkout.scanItem(item1);
        Assertions.assertEquals(50,checkout.totalPrice());
        checkout.emptyCart();

        //3.cart->"AB"
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        Assertions.assertEquals(80,checkout.totalPrice());
        checkout.emptyCart();

        //4.cart->"CDBA"
        checkout.scanItem(item3);
        checkout.scanItem(item4);
        checkout.scanItem(item2);
        checkout.scanItem(item1);

        Assertions.assertEquals(115,checkout.totalPrice());
        checkout.emptyCart();

        //5.cart->"CDBA"
        checkout.scanItem(item3);
        checkout.scanItem(item4);
        checkout.scanItem(item2);
        checkout.scanItem(item1);

        Assertions.assertEquals(115,checkout.totalPrice());
        checkout.emptyCart();

        //6.cart->"AA"
        checkout.scanItem(item1);
        checkout.scanItem(item1);

        Assertions.assertEquals(100,checkout.totalPrice());
        checkout.emptyCart();

        //7.cart->"AAA"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);

        Assertions.assertEquals(130,checkout.totalPrice());
        checkout.emptyCart();

        //8.cart->"AAAA"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);

        Assertions.assertEquals(180,checkout.totalPrice());
        checkout.emptyCart();

        //9.cart->"AAAAA"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);

        Assertions.assertEquals(230,checkout.totalPrice());
        checkout.emptyCart();

        //10.cart->"AAAAAA"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);

        Assertions.assertEquals(260,checkout.totalPrice());
        checkout.emptyCart();

        //10.cart->"AAAB"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item2);

        Assertions.assertEquals(160,checkout.totalPrice());
        checkout.emptyCart();

        //11.cart->"AAABB"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        checkout.scanItem(item2);

        Assertions.assertEquals(175,checkout.totalPrice());
        checkout.emptyCart();

        //11.cart->"AAABBD"
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        checkout.scanItem(item2);
        checkout.scanItem(item4);

        Assertions.assertEquals(190,checkout.totalPrice());
        checkout.emptyCart();

        //11.cart->"DABABA"
        checkout.scanItem(item4);
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        checkout.scanItem(item1);
        checkout.scanItem(item2);
        checkout.scanItem(item1);

        Assertions.assertEquals(190,checkout.totalPrice());
        checkout.emptyCart();

    }
}
