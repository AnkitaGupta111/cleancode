package example.kata01.order;

import lombok.Data;

@Data
public class OrderItem {
    int itemId;
    int orderQuantity;
    int discountedQuantity;
    int price;
    int discountedPrice;

    public OrderItem(int itemId, int orderQuantity, int discountedQuantity, int price, int discountedPrice) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
        this.discountedQuantity = discountedQuantity;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public OrderItem(int itemId) {
        this.itemId = itemId;
    }
}
