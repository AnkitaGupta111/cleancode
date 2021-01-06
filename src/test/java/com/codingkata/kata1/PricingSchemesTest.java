package com.codingkata.kata1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(JUnit4.class)
public class PricingSchemesTest {
    PricingSchemes pricingSchemes=new PricingSchemes();
    @Test
    public void testPricingSchemes(){
        pricingSchemes.scanItem("A");
        pricingSchemes.scanItem("A");
        pricingSchemes.scanItem("A");
        pricingSchemes.scanItem("A");
        pricingSchemes.scanItem("A");
        pricingSchemes.scanItem("A");

        pricingSchemes.scanRules("A 50 3 130");
        double totalPrice= pricingSchemes.total();
        Assert.assertEquals(260,pricingSchemes.total(),0);

    }
}
