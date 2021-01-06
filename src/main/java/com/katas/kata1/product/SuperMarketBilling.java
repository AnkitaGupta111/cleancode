package com.katas.kata1.product;

import com.katas.kata1.ICheckout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperMarketBilling implements ICheckout {

    /**
     * This method sums the total bill with discount
     * @param orders items ordered
     * @param pricingRules  pricing rules
     * @return total amount
     */
    @Override
    public int billing(String orders, List<String> pricingRules) {
        Map<Character, Integer> itemCount = new HashMap<>();

        char[] items = orders.toCharArray();

        for (char item : items) {
            if (itemCount.containsKey(item)) {
                itemCount.put(item, itemCount.get(item) + 1);
            } else {
                itemCount.put(item, 1);
            }
        }

        int totalPrice = 0;
        for (String pricingRule : pricingRules) {
            String[] rule = pricingRule.split(",");
            String[] itemPrice = rule[0].split(":");
            String[] offer = rule[1].split("-");
            char item = itemPrice[0].charAt(0);
            int singleUnitPrice = Integer.parseInt(itemPrice[1]);
            int offerPriceUnits = Integer.parseInt(offer[0]);
            int offerPrice = Integer.parseInt(offer[1]);

            if (itemCount.containsKey(item)) {
                int itemsCount = itemCount.get(item);
                int calculatedPrice = offerPrice != 0
                        ? (itemsCount / offerPriceUnits) * offerPrice + (itemsCount - offerPriceUnits * (itemsCount / offerPriceUnits)) * singleUnitPrice
                        : singleUnitPrice;
                totalPrice = totalPrice + calculatedPrice;
            }
        }
        return totalPrice;
    }
}
