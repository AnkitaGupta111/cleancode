package com.katas.kata1.approach2;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperMarketBilling implements ISuperMarketBilling {

    private List<String> pricingRules;
    private double totalBill = 0;


    /**
     * scans pricing rules
     *
     * @param pricingRules pricing rules associated with items
     */
    @Override
    public void scanPricingRules(List<String> pricingRules) {
        this.pricingRules = pricingRules;
    }

    /**
     * calculates bill of all items ordered
     *
     * @param orders items ordered
     * @return total amount
     */
    @Override
    public double billing(String orders) {
        Map<Character, Long> itemsCountMap;
        totalBill = 0;
        Stream<Character> orderStream = orders.chars().mapToObj(order -> (char) order);
        itemsCountMap = orderStream.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
        pricingRules.forEach(pricingRule -> {
            String[] ruleValues = pricingRule.split("-");
            int offerItemsNumber = Integer.parseInt(ruleValues[2]);
            double singleItemCost = Double.parseDouble(ruleValues[1]);
            double offerCost = Double.parseDouble(ruleValues[3]);
            char orderItem = ruleValues[0].charAt(0);
            if (itemsCountMap.containsKey(orderItem)) {
                long itemCount = itemsCountMap.get(orderItem);
                if (offerItemsNumber != 0) {
                    getBillWithDiscount(itemCount, offerItemsNumber, offerCost, singleItemCost);
                } else {
                    getBillWithOutDiscount(itemCount, singleItemCost);
                }
            }
        });
        return totalBill;
    }

    /**
     * method to calculate bill of similar items when discount is applied
     *
     * @param itemCount        item count
     * @param offerItemsNumber number of similar items with offer
     * @param offerCost        offer cost
     * @param singleItemCost   single item cost
     */
    private void getBillWithDiscount(long itemCount, int offerItemsNumber, double offerCost, double singleItemCost) {
        totalBill += (itemCount / offerItemsNumber) * offerCost + (itemCount % offerItemsNumber) * singleItemCost;
    }

    /**
     * method to calculate bill of similar items when no discount is available
     *
     * @param itemCount      item count
     * @param singleItemCost single item cost
     */
    private void getBillWithOutDiscount(long itemCount, double singleItemCost) {
        totalBill += itemCount * singleItemCost;
    }
}

