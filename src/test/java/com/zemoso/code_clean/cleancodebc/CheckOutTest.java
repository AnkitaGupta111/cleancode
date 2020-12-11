package com.zemoso.code_clean.cleancodebc;

import com.zemoso.code_clean.kata1.config.LoadMasterData;
import com.zemoso.code_clean.kata1.product.ICheckout;
import com.zemoso.code_clean.kata1.product.solution1.Checkout;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckOutTest {
    @Before
    public void before() {
        new LoadMasterData().load();
    }

    @Test
    public void testTotals() {
        assertEquals(0.0, price(""), 0);
        assertEquals(50, price("A"), 0);
        assertEquals(80, price("A,B"), 0);
        assertEquals(115, price("C,D,B,A"), 0);
        assertEquals(100, price("A,A"), 0);
        assertEquals(130, price("A,A,A"), 0);
        assertEquals(180, price("A,A,A,A"), 0);
        assertEquals(95, price("B,A,B"), 0);
        assertEquals(230, price("A,A,A,A,A"), 0);
        assertEquals(260, price("A,A,A,A,A,A"), 0);
        assertEquals(160, price("A,A,A,B"), 0);
        assertEquals(175, price("A,A,A,B,B"), 0);
        assertEquals(190, price("A,A,A,B,B,D"), 0);
        assertEquals(190, price("D,A,B,A,B,A"), 0);
    }

    @Test
    public void testIncrementals() {
        ICheckout co = new Checkout(LoadMasterData.pricingRules);
        assertEquals(0, co.total(), 0);
        co.scan("A");
        assertEquals(50, co.total(), 0);
        co.scan("B");
        assertEquals(80, co.total(), 0);
        co.scan("A");
        assertEquals(130, co.total(), 0);
        co.scan("A");
        assertEquals(160, co.total(), 0);
        co.scan("B");
        assertEquals(175, co.total(), 0);
    }

    @Test
    public void testRemovingDiscountedItem() {
        ICheckout co = new Checkout(LoadMasterData.pricingRules);
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        assertEquals(260, co.total(), 0);
        System.out.println(co.remove("A"));
        assertEquals(230, co.total(), 0);
    }
    @Test
    public void testRemovingNonDiscountedItem() {
        ICheckout co = new Checkout(LoadMasterData.pricingRules);
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        co.scan("A");
        assertEquals(310, co.total(), 0);
        System.out.println(co.remove("A"));
        assertEquals(260, co.total(), 0);
    }

    private Double price(String s) {
        ICheckout co = new Checkout(LoadMasterData.pricingRules);
        System.out.println(s);
        for (String str : s.split(",")) {
            if (s != null && s != "") {
                co.scan(str);
            }
        }
        return co.total();
    }
}
