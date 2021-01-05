package com.katas.kata1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SuperMarketOffers implements CheckoutSystem {
    public String items;
    public List<String> newRules;

    /**
     * scans the item
     * @param items ,, billing items given as input
     */
    @Override
    public void scanInputs(String items) {
        this.items = items;
    }

    /**
     * scans the newRule
     * @param newRules ,, rules in the form of Strings where itemName, unitPrice, offerItems, offerPrice are separated by hyphens
     */
    @Override
    public void scanNewRules(List<String> newRules) {
        this.newRules = newRules;
    }

    /**
     * counts the number of occurrences of each item
     * @param orderedItems order items for billings
     * @return itemsMap of each item and its respective occurrence
     */
    public Map<Character, Long> countEachItem(String orderedItems) {
        IntStream orderItemsIntStream = orderedItems.chars();

        Stream<Character> orderItemsStream = orderItemsIntStream.mapToObj(ch -> (char) ch);

        return orderItemsStream.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
    }

    /**
     * calculates the billing of items with their respective offers
     *
     * @return total amount of billing items
     */
    @Override
    public double calculateBillingOfItems() {

        Map<Character, Long> itemsMap = countEachItem(items);

        double total = 0;
        for (String newRule : newRules) {
            String[] newRuleValue = newRule.split("-");
            char item = newRuleValue[0].charAt(0);
            int singleUnitPrice = Integer.parseInt(newRuleValue[1]);
            int offerPriceUnits = Integer.parseInt(newRuleValue[2]);
            int offerPrice = Integer.parseInt(newRuleValue[3]);

            if (itemsMap.containsKey(item)) {
                long itemsCount = itemsMap.get(item);
                if (offerPrice != 0)
                    total = total + ((itemsCount / offerPriceUnits) * offerPrice) + (itemsCount % offerPriceUnits * singleUnitPrice);
                else
                    total = total + itemsCount * singleUnitPrice;
            }
        }

        return total;
    }
}
