package com.cleancode.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import com.cleancode.shoppingcart.offers.IOffer;
import com.cleancode.shoppingcart.ImmutableItem;

public class Cart {
    private List<ImmutableItem> items = new ArrayList<>();

    public double getTotal() {
        return items.stream().mapToDouble(ImmutableItem::getCalculatedCost).sum();
    }

    public int getBasketSize() {
        return items.size();
    }

    public void addItemsToBasket(ImmutableItem immutableItem) {
        assert immutableItem != null : "Item to be added to basket cannot be null";

        ImmutableItem updatedItem = immutableItem.withCalculatedCost(immutableItem.getQuantity()*immutableItem.getUnitCost());
        items.add(updatedItem);
    }

    /**
     * applyOfferToBasket finds the given item from the list and applies the provided offer
     * @param offer
     * @param itemName
     */
    public void applyOfferToBasket(IOffer offer, String itemName) {
        assert offer != null : "Offer to be applied should not be null";
        assert itemName!=null : "itemName should not be null";

        ImmutableItem item = findItemByItemName(itemName);
        if(item == null){
            System.out.println(String.format("Offer item %s does not exist in the basket", itemName));
            return;
        }

        ImmutableItem offerAppliedItem = offer.apply(item);
        items.set(items.indexOf(item),offerAppliedItem);
    }

    private ImmutableItem findItemByItemName(String itemName) {

        ImmutableItem immutableItem = items.stream()
                .filter(item -> itemName.equals(item.getName()))
                .findAny()
                .orElse(null);

        return immutableItem;
    }

}
