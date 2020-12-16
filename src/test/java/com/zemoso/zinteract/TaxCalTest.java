package com.zemoso.zinteract;

import com.zemoso.zinteract.services.taxcalculation.TaxCal;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TaxCalTest {

    @Test
    public void testTaxCalBelow25K() {
        String json = TaxCal.getRulesJson();
        HashMap<String, String> value = new HashMap<String, String>();
        value.put("investment_80c", "1000");
        value.put("income", "249000");
        Double tax = TaxCal.execute(value, json);
        assertTrue(tax == 0);
    }


    @Test
    public void testTaxCal25KTo50K() {
        String json = TaxCal.getRulesJson();
        HashMap<String, String> value = new HashMap<String, String>();
        value.put("investment_80c", "30000");
        value.put("income", "310000");
        Double tax = TaxCal.execute(value, json);
        assertTrue(tax == 1030.20);
    }

    @Test
    public void testTaxCal50kTo100K() {
        String json = TaxCal.getRulesJson();
        HashMap<String, String> value = new HashMap<String, String>();
        value.put("investment_80c", "10000");
        value.put("income", "1000000");
        Double tax = TaxCal.execute(value, json);
        assertTrue(tax == 126714.60);
    }

    @Test
    public void testTaxCalAbove100K() {
        String json = TaxCal.getRulesJson();
        HashMap<String, String> value = new HashMap<String, String>();
        value.put("investment_80c", "10000");
        value.put("income", "1100000");
        Double tax = TaxCal.execute(value, json);
        assertTrue(tax == 156590.40);
    }

    @Test
    public void testTaxCalMain() {
        String json = TaxCal.getRulesJson();
        HashMap<String, String> value = new HashMap<String, String>();
        value.put("investment_80c", "10000");
        value.put("income","510000");
        Double tax = TaxCal.execute(value, json);
        assertTrue(tax == 23694.60);
    }
}
