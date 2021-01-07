package com.katas.kata1.approach2;

import java.util.List;

/**
 * declares methods to scans pricing rules and calculate the total bill
 */
public interface ISuperMarketBilling {
    /**
     * scans pricing rules
     * @param pricingRules pricing rules assosciated with items
     */
    void scanPricingRules(List<String> pricingRules);

    /**
     * @param orders items ordered
     * @return total amount
     */
    double billing(String orders);
}
