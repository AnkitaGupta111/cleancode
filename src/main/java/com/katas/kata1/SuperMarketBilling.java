package com.katas.kata1;

import java.util.HashMap;
import java.util.List;

public class SuperMarketBilling {
    /**
     *
     * @param orders items ordered
     * @return total amount
     */
    public int billing(String orders) {
        HashMap<Character, Integer> itemsCount = new HashMap<>();
        char item;
        int totalBill = 0;
        for (int i = 0; i < orders.length(); i++) {
            item = orders.charAt(i);
            if (itemsCount.containsKey(item)) {
                itemsCount.put(item, itemsCount.get(item) + 1);
            } else {
                itemsCount.put(item, 1);
            }
        }
        if (itemsCount.containsKey('A')) {
            int itemsA = itemsCount.get('A');
            totalBill = totalBill + (itemsA / 3) * 130 + (itemsA - 3 * (itemsA / 3)) * 50;
        }
        if (itemsCount.containsKey('B')) {
            int itemsB = itemsCount.get('B');
            totalBill = totalBill + (itemsB / 2) * 45 + (itemsB - 2 * (itemsB / 2)) * 30;
        }
        if (itemsCount.containsKey('C')) {
            totalBill = totalBill + itemsCount.get('C') * 20;

        }
        if (itemsCount.containsKey('D')) {
            totalBill = totalBill + itemsCount.get('D') * 15;

        }

        return totalBill;
    }

    /**
     *
     * @param orders items ordered
     * @param rules  pricing rules ,rule format is singleItemCost,offerItemsNumber-offerCost
     * @return total amount
     */
    public int billingWithVaryingRules(String orders, List<String> rules) {
        HashMap<Character, Integer> itemsCount = new HashMap<>();
        char item;
        int totalBill = 0;
        for (int index= 0; index < orders.length(); index++) {
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
