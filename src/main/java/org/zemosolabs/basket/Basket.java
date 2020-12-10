package org.zemosolabs.basket;

import org.zemosolabs.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Item> items;

    public Basket() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if(items.contains(item)) {
            item.setQuantity(item.getQuantity()+1);
        } else {
            items.add(item);
            item.setQuantity(1);
        }
    }

    public void removeItem(Item item) {
        if(items.contains(item)) {
            item.setQuantity(item.getQuantity()-1);
        }
    }

}
