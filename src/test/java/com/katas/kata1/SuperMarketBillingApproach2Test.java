package com.katas.kata1;

import com.katas.kata1.approach2.SuperMarketBilling;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
class SuperMarketBillingApproach2Test {

    private final SuperMarketBilling superMarketBilling = new SuperMarketBilling();

    @Test
    void testBilling() {
        List<String> rules = new ArrayList<String>();
        rules.add("A-50-3-130");
        rules.add("B-30-2-45");
        rules.add("C-20-0-0");
        rules.add("D-15-0-0");
        superMarketBilling.scanPricingRules(rules);
        Assertions.assertEquals(0, superMarketBilling.billing(""));
        Assertions.assertEquals(50, superMarketBilling.billing("A"));
        Assertions.assertEquals(80, superMarketBilling.billing("AB"));
        Assertions.assertEquals(115, superMarketBilling.billing("CDBA"));
        Assertions.assertEquals(100, superMarketBilling.billing("AA"));
        Assertions.assertEquals(130, superMarketBilling.billing("AAA"));
        Assertions.assertEquals(180, superMarketBilling.billing("AAAA"));
        Assertions.assertEquals(230, superMarketBilling.billing("AAAAA"));
        Assertions.assertEquals(260, superMarketBilling.billing("AAAAAA"));
        Assertions.assertEquals(160, superMarketBilling.billing("AAAB"));
        Assertions.assertEquals(175, superMarketBilling.billing("AAABB"));
        Assertions.assertEquals(190, superMarketBilling.billing("AAABBD"));
        Assertions.assertEquals(190, superMarketBilling.billing("DABABA"));
        Assertions.assertEquals(190, superMarketBilling.billing("DABABA"));
    }
}