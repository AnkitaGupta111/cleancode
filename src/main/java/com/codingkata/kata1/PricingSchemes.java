package com.codingkata.kata1;

import java.util.*;

public class PricingSchemes implements Checkout{
    public static List<String> orderedItems=new ArrayList<>();
    public static Set<String> pricingRules=new HashSet<>();

    @Override
    public void scanItem(String item) {
        orderedItems.add(item);
    }

    @Override
    public void scanRules(String rule){
       pricingRules.add(rule);
    }

    /**
     *
     * @param orderedItems items ordered
     * @return Items with respective counts
     */
    public Map<String,Integer> countOfEachItem(List<String> orderedItems){
        Map<String,Integer> itemsCountMap=new HashMap<>();
        for(String item:orderedItems){
            if(itemsCountMap.containsKey(item))
                itemsCountMap.put(item,itemsCountMap.get(item)+1);
            else
                itemsCountMap.put(item,1);
        }
        return  itemsCountMap;
    }

    /**
     *
     * @return total amount
     */
    @Override
    public double total() {
        double totalPrice=0;
        Map<String,Integer> itemsCountMap=countOfEachItem(orderedItems);
        for(String rule: pricingRules){
            String [] itemRules=rule.split(" ");
            String item=itemRules[0];
            int singleItemPrice=Integer.parseInt(itemRules[1]);
            int countOfItemInOffer=Integer.parseInt(itemRules[2]);
            int priceOfItemInOffer=Integer.parseInt(itemRules[3]);
            if(itemsCountMap.containsKey(item)){
                int itemCount=itemsCountMap.get(item);
                if(countOfItemInOffer!=0 || priceOfItemInOffer!=0){
                    totalPrice+=((itemCount/countOfItemInOffer)*priceOfItemInOffer)+(itemCount%countOfItemInOffer*singleItemPrice);
                }
                else
                    totalPrice+=itemCount*singleItemPrice;

            }
        }
        return totalPrice;
    }

}

