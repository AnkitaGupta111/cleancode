package com.zemoso.zinteract;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

	@Test
	public void testTaxCalBelow25K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<>();
		value.put("investment_80c", "1000");
		value.put("income", "249000");
		double tax = TaxCalculator.execute(value, json);
		assertEquals(0, tax, 0.0);

	}

	@Test
	public void testTaxCal25KTo50K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<>();
		value.put("investment_80c", "30000");
		value.put("income", "310000");
		double tax = TaxCalculator.execute(value, json);
		assertEquals(1030.20, tax, 0.0);
	}

	@Test
	public void testTaxCal50kTo100K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<>();
		value.put("investment_80c", "10000");
		value.put("income", "1000000");
		double tax = TaxCalculator.execute(value, json);
		assertEquals(126714.60, tax, 0.0);
	}

	@Test
	public void testTaxCalAbove100K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<>();
		value.put("investment_80c", "10000");
		value.put("income", "1100000");
		double tax = TaxCalculator.execute(value, json);
		assertEquals(156590.40, tax, 0.0);
	}
}
