package com.zemoso.zinteract;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TaxCalculatorTest {

	@Test
	public void testTaxCalBelow25K() {
		String json = TaxCalculator.getRulesJson();
		Map<String, String> value = new HashMap<>();
		value.put("investment_80c", "1000");
		value.put("income", "249000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 0);

	}

	@Test
	public void testTaxCal25KTo50K() {
		String json = TaxCalculator.getRulesJson();
		Map<String, String> value = new HashMap<>();
		value.put("investment_80c", "30000");
		value.put("income", "310000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 1030.20);
	}

	@Test
	public void testTaxCal50kTo100K() {
		String json = TaxCalculator.getRulesJson();
		Map<String, String> value = new HashMap<>();
		value.put("investment_80c", "10000");
		value.put("income", "1000000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 126714.60);
	}

	@Test
	public void testTaxCalAbove100K() {
		String json = TaxCalculator.getRulesJson();
		Map<String, String> value = new HashMap<>();
		value.put("investment_80c", "10000");
		value.put("income", "1100000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 156590.40);
	}

}
