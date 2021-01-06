package com.katas.kata1;

import com.katas.kata1.product.SuperMarketBilling;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
class SuperMarketBillingTest {

    private final ICheckout superMarketBilling = new SuperMarketBilling();

    @Test
    void testBilling() {
        List<String> rules = new ArrayList<>();
        rules.add("A:50,3-130");
        rules.add("B:30,2-45");
        rules.add("C:20,0-0");
        rules.add("D:15,0-0");
        Assert.assertEquals(0, superMarketBilling.billing("", rules));
        Assert.assertEquals(50, superMarketBilling.billing("A", rules));
        Assert.assertEquals(80, superMarketBilling.billing("AB", rules));
        Assert.assertEquals(115, superMarketBilling.billing("CDBA", rules));
        Assert.assertEquals(100, superMarketBilling.billing("AA", rules));
        Assert.assertEquals(130, superMarketBilling.billing("AAA", rules));
        Assert.assertEquals(180, superMarketBilling.billing("AAAA", rules));
        Assert.assertEquals(230, superMarketBilling.billing("AAAAA", rules));
        Assert.assertEquals(260, superMarketBilling.billing("AAAAAA", rules));
        Assert.assertEquals(160, superMarketBilling.billing("AAAB", rules));
        Assert.assertEquals(175, superMarketBilling.billing("AAABB", rules));
        Assert.assertEquals(190, superMarketBilling.billing("AAABBD", rules));
        Assert.assertEquals(190, superMarketBilling.billing("DABABA", rules));
        Assert.assertEquals(190, superMarketBilling.billing("DABABA", rules));
    }
}