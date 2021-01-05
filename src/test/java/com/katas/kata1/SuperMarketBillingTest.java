package com.katas.kata1;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
class SuperMarketBillingTest {

    private final SuperMarketBilling superMarketBilling = new SuperMarketBilling();


    @Test
    void testBilling() {
        Assert.assertEquals(0, superMarketBilling.billing(""));
        Assert.assertEquals(50, superMarketBilling.billing("A"));
        Assert.assertEquals(80, superMarketBilling.billing("AB"));
        Assert.assertEquals(115, superMarketBilling.billing("CDBA"));
        Assert.assertEquals(100, superMarketBilling.billing("AA"));
        Assert.assertEquals(130, superMarketBilling.billing("AAA"));
        Assert.assertEquals(180, superMarketBilling.billing("AAAA"));
        Assert.assertEquals(230, superMarketBilling.billing("AAAAA"));
        Assert.assertEquals(260, superMarketBilling.billing("AAAAAA"));
        Assert.assertEquals(160, superMarketBilling.billing("AAAB"));
        Assert.assertEquals(175, superMarketBilling.billing("AAABB"));
        Assert.assertEquals(190, superMarketBilling.billing("AAABBD"));
        Assert.assertEquals(190, superMarketBilling.billing("DABABA"));
    }

    @Test
    void testBillingWithVaryingRules() {
        List<String> rules = new ArrayList<String>();
        rules.add("50,3-130");
        rules.add("30,2-45");
        rules.add("20,0-0");
        rules.add("15,0-0");
        Assert.assertEquals(0, superMarketBilling.billingWithVaryingRules("", rules));
        Assert.assertEquals(50, superMarketBilling.billingWithVaryingRules("A", rules));
        Assert.assertEquals(80, superMarketBilling.billingWithVaryingRules("AB", rules));
        Assert.assertEquals(115, superMarketBilling.billingWithVaryingRules("CDBA", rules));
        Assert.assertEquals(100, superMarketBilling.billingWithVaryingRules("AA", rules));
        Assert.assertEquals(130, superMarketBilling.billingWithVaryingRules("AAA", rules));
        Assert.assertEquals(180, superMarketBilling.billingWithVaryingRules("AAAA", rules));
        Assert.assertEquals(230, superMarketBilling.billingWithVaryingRules("AAAAA", rules));
        Assert.assertEquals(260, superMarketBilling.billingWithVaryingRules("AAAAAA", rules));
        Assert.assertEquals(160, superMarketBilling.billingWithVaryingRules("AAAB", rules));
        Assert.assertEquals(175, superMarketBilling.billingWithVaryingRules("AAABB", rules));
        Assert.assertEquals(190, superMarketBilling.billingWithVaryingRules("AAABBD", rules));
        Assert.assertEquals(190, superMarketBilling.billingWithVaryingRules("DABABA", rules));
        Assert.assertEquals(190, superMarketBilling.billingWithVaryingRules("DABABA", rules));
    }
}