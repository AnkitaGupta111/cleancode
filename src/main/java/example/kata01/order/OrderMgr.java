package example.kata01.order;


import example.kata01.DiscountMgr;

import java.util.HashMap;
import java.util.Map;

public class OrderMgr {
    DiscountMgr discountMgr;
    Map<Integer, OrderItem> orders;
    int totalOrderValue;
    int totalOrderItems;

    public OrderMgr() {
        this.discountMgr = new DiscountMgr();
        this.orders = new HashMap<>();
    }

    public void addItem(int itemId, int quantity, int price) {
        OrderItem orderItem;
        if(orders.containsKey(itemId)) {
            orderItem = orders.get(itemId);
            orderItem.setOrderQuantity(orderItem.getOrderQuantity() + quantity);
        }else {
            orderItem = new OrderItem(itemId);
            orderItem.setPrice(price);
            orderItem.setOrderQuantity(quantity);
        }
        discountMgr.applyDiscount(orderItem);
        orders.put(itemId, orderItem);
    }

    public void removeItem(int itemId,int quantity) {
        OrderItem orderItem;
        if(orders.containsKey(itemId)) {
            orderItem = orders.get(itemId);
            orderItem.setOrderQuantity(orderItem.getOrderQuantity() - quantity);
            if(orderItem.getOrderQuantity() == 0) {
                orders.remove(itemId);
            }else {
                discountMgr.applyDiscount(orderItem);
            }
        }
    }

    public void calcOrder() {
        for (Integer orderId : orders.keySet()) {
            totalOrderValue += getFinalPrice(orders.get(orderId));
            totalOrderItems += getFinalQuantity(orders.get(orderId));
        }
        System.out.println("totalOrderValue --->" +totalOrderValue );
        System.out.println("totalOrderItems --->" +totalOrderItems );
    }

    int getFinalPrice(OrderItem orderItem) {
        return (orderItem.getPrice()*orderItem.getOrderQuantity() - orderItem.getDiscountedPrice());
    }

    int getFinalQuantity(OrderItem orderItem) {
        return (orderItem.getOrderQuantity() + orderItem.getDiscountedQuantity());
    }
}
