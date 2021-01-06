package com.katas.kata1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperMarketBilling {


    /**
     * @param orders items ordered
     * @param rules  pricing rules ,rule format is singleItemCost,offerItemsNumber-offerCost
     * @return total amount
     */
    public int billingWithVaryingRules(String orders, List<String> rules) {
        Map<Character, Integer> itemsCount = new HashMap<>();
        char item;
        int totalBill = 0;
        for (int index = 0; index < orders.length(); index++) {
            item = orders.charAt(index);
            if (itemsCount.containsKey(item)) {
                itemsCount.put(item, itemsCount.get(item) + 1);
            } else {
                itemsCount.put(item, 1);
            }
        }
        for (int index = 0; index < rules.size(); index++) {
            int offerItemsNumber = Integer.parseInt(rules.get(index).substring(rules.get(index).indexOf(",") + 1, rules.get(index).indexOf("-")));
            int singleItemCost = Integer.parseInt(rules.get(index).substring(0, rules.get(index).indexOf(",")));
            int offerCost = Integer.parseInt(rules.get(index).substring(rules.get(index).indexOf("-") + 1));
            char orderItem = (char) ('A' + index);
            if (itemsCount.containsKey(orderItem)) {
                int items = itemsCount.get(orderItem);
                if (offerItemsNumber != 0) {
                    totalBill = totalBill + (items / offerItemsNumber) * offerCost + (items - offerItemsNumber * (items / offerItemsNumber)) * singleItemCost;
                } else {
                    totalBill = totalBill + items * singleItemCost;
                }
            }
        }
        return totalBill;
    }
}
