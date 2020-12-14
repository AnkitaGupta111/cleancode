package example.kata01;

import example.kata01.order.OrderItem;
import example.kata01.ruleApp.DisountRule;
import example.kata01.ruleApp.RuleManager;

import java.util.List;

public class DiscountMgr {

    RuleManager ruleManager;

    public DiscountMgr() {
        ruleManager = new RuleManager();
        ruleManager.initialize();
    }

    public void applyDiscount(OrderItem orderItem) {
        List<DisountRule> rules = ruleManager.getRule(orderItem.getItemId());
        for (DisountRule rule : rules) {
            if(rule.getDiscountPercentage() != null) {
                rule.getDiscountPercentage().ifPresent(percentage -> {
                    orderItem.setDiscountedPrice(calculatePercentage(orderItem.getPrice(),percentage));
                });
            }


            if(rule.getOrderQuantity() != null) {
                rule.getOrderQuantity().ifPresent(orderQuantity -> {
                    if(orderItem.getOrderQuantity() == orderQuantity) {
                        orderItem.setDiscountedQuantity(rule.getDiscountQuantity().get());
                    }
                });
            }

            if(rule.getDiscountPrice() != null) {
                rule.getDiscountPrice().ifPresent(orderItem::setDiscountedPrice);
            }
        }
    }

    int calculatePrice(int price, int discountPrice) {
        return price-discountPrice;
    }

    int calculatePercentage(int price,int percentage) {
        return (price*percentage)/100;
    }
}
