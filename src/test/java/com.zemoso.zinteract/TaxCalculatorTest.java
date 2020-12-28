package com.zemoso.zinteract;

import com.zemoso.zinteract.comparators.abstractfactory.AbstractComparatorFactory;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertTrue;

public class TaxCalculatorTest {

	@BeforeEach
	void setUp()
	{
		org.springframework.test.util.ReflectionTestUtils.setField(AbstractComparatorFactory.class,
				"comparatorFactoryClass", "com.zemoso.zinteract.comparators.abstractfactory.factory.ComparatorFactory");
	}

	@Test
	public void testTaxCalBelow25K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<String, String>();
		value.put("investment_80c", "1000");
		value.put("income", "249000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 0);

	}

	@Test
	public void testTaxCal25KTo50K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<>();
		value.put("investment_80c", "30000");
		value.put("income", "310000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 1030.20);
	}

	@Test
	public void testTaxCal50kTo100K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<String, String>();
		value.put("investment_80c", "10000");
		value.put("income", "1000000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 126714.60);
	}

	@Test
	public void testTaxCalAbove100K() {
		String json = TaxCalculator.getRulesJson();
		HashMap<String, String> value = new HashMap<String, String>();
		value.put("investment_80c", "10000");
		value.put("income", "1100000");
		Double tax = TaxCalculator.execute(value, json);
		assertTrue(tax == 156590.40);
	}

}
