package com.zemoso.code_clean.kata1.product.solution1;

import com.zemoso.code_clean.kata1.model.Discount;
import com.zemoso.code_clean.kata1.model.Product;
import com.zemoso.code_clean.kata1.product.ICheckout;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Checkout implements ICheckout {
    private final Map<String, Product> pricingRules;
    public final Map<String, Integer> cart;
    private Double totalPrice = 0.0;

    public Checkout(Map<String, Product> pricingRules) {
        this.pricingRules = pricingRules;
        this.cart = new HashMap<>();
    }

    public void scan(String item) {
        Integer count = cart.get(item);
        if ((count == null)) {
            cart.put(item, 1);
        } else {
            cart.put(item, ++count);
        }
        Product product = pricingRules.get(item);
        Double actualPrice = product.getPrice();
        List<Discount> discountList = product.getDiscount();
        if (discountList != null && count != null) {
            for (Discount discount : discountList) {
                Integer quantity = discount.getQuantity();
                Integer discountedQuantity =
                        ((count > quantity) && ((count % quantity) == 0)) ?
                                (count - quantity) : count;

                if (discountedQuantity.equals(quantity)) {
                    totalPrice = (totalPrice - ((actualPrice) * (discountedQuantity - 1))) + discount.getPrice();
                } else {
                    totalPrice += actualPrice;
                }
            }
        } else {
            totalPrice += actualPrice;
        }
    }

    public Double total() {
        return totalPrice;
    }

    @Override
    public String remove(String item) {
        Integer cartQuantity = cart.get(item);
        Double legacyPrice = totalPrice;
        if (cartQuantity == null) {
            return "Item not added to cart";
        }
        Product product = pricingRules.get(item);
        Double actualPrice = product.getPrice();
        List<Discount> discountList = product.getDiscount();
        if (discountList == null) {
            totalPrice -= actualPrice;
        }
        for (Discount discount : discountList) {
            Integer discountQuantity = discount.getQuantity();
            Double discountPrice = discount.getPrice();
            if ((cartQuantity % discountQuantity) == 0) {
                totalPrice = (totalPrice - discountPrice) + ((discountQuantity - 1) * actualPrice);
            } else {
                totalPrice -= actualPrice;
            }
        }
        cart.remove(item);
        return "Item "+item+" removed, TotalPrice changed from "+legacyPrice+" to "+totalPrice;
    }
}
