package com.katas.kata1.approach2;


import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperMarketBilling implements ISuperMarketBilling {

    public List<String> pricingRules;


    /**
     * scans pricing rules
     *
     * @param pricingRules pricing rules assosciated with items
     */
    @Override
    public void scanPricingRules(List<String> pricingRules) {
        this.pricingRules = pricingRules;
    }

    /**
     * @param orders items ordered
     * @return total amount
     */
    @Override
    public double billing(String orders) {
        Map<Character, Long> itemsCount;
        AtomicReference<Double> totalBill = new AtomicReference<>(0.0);
        Stream<Character> orderStream = orders.chars().mapToObj(order -> (char) order);
        itemsCount = orderStream.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
        Map<Character, Long> finalItemsCount = itemsCount;
        pricingRules.forEach(pricingRule -> {
            String[] ruleValues = pricingRule.split("-");
            int offerItemsNumber = Integer.parseInt(ruleValues[2]);
            int singleItemCost = Integer.parseInt(ruleValues[1]);
            int offerCost = Integer.parseInt(ruleValues[3]);
            char orderItem = ruleValues[0].charAt(0);
            if (finalItemsCount.containsKey(orderItem)) {
                long items = finalItemsCount.get(orderItem);
                if (offerItemsNumber != 0) {
                    totalBill.updateAndGet(v -> (double) (v + (items / offerItemsNumber) * offerCost + (items - offerItemsNumber * (items / offerItemsNumber)) * singleItemCost));
                } else {
                    totalBill.updateAndGet(v -> (double) (v + items * singleItemCost));
                }
            }
        });
        return totalBill.get();
    }
}

