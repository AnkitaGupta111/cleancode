package com.katas.kata1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class SuperMarketOffersTest {

    SuperMarketOffers superMarketOffers = new SuperMarketOffers();

    @Test
    public void testBillings() {
        List<String> newRules = new ArrayList<>();
        newRules.add("A-50-3-130");
        newRules.add("B-30-2-45");
        newRules.add("C-20-0-0");
        newRules.add("D-15-0-0");
        newRules.add("E-23-2-30");
        superMarketOffers.scanNewRules(newRules);

        superMarketOffers.scanInputs("E");
        Assert.assertEquals(23, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("EE");
        Assert.assertEquals(30, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("EEE");
        Assert.assertEquals(53, superMarketOffers.calculateBillingOfItems(), 0);

        superMarketOffers.scanInputs("");
        Assert.assertEquals(0, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("A");
        Assert.assertEquals(50, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AB");
        Assert.assertEquals(80, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("CDBA");
        Assert.assertEquals(115, superMarketOffers.calculateBillingOfItems(), 0);

        superMarketOffers.scanInputs("A");
        Assert.assertEquals(50, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AAA");
        Assert.assertEquals(130, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AAAA");
        Assert.assertEquals(180, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AAAAA");
        Assert.assertEquals(230, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AAAAAA");
        Assert.assertEquals(260, superMarketOffers.calculateBillingOfItems(), 0);

        superMarketOffers.scanInputs("AAAB");
        Assert.assertEquals(160, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AAABB");
        Assert.assertEquals(175, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("AAABBD");
        Assert.assertEquals(190, superMarketOffers.calculateBillingOfItems(), 0);
        superMarketOffers.scanInputs("DABABA");
        Assert.assertEquals(190, superMarketOffers.calculateBillingOfItems(), 0);
    }
}
