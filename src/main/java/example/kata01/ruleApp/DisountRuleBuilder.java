package example.kata01.ruleApp;

import java.util.Optional;

public class DisountRuleBuilder {
    private int ruleId;
    private Optional<Integer> orderQuanity;
    private Optional<Integer> discountQuantity;
    private Optional<Integer> discountPrice;
    private Optional<Integer> discountPercentage;

    public DisountRuleBuilder setRuleId(int ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public DisountRuleBuilder setOrderQuanity(Optional<Integer> orderQuanity) {
        this.orderQuanity = orderQuanity;
        return this;
    }

    public DisountRuleBuilder setDiscountQuantity(Optional<Integer> discountQuantity) {
        this.discountQuantity = discountQuantity;
        return this;
    }

    public DisountRuleBuilder setDiscountPrice(Optional<Integer> discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public DisountRuleBuilder setDiscountPercentage(Optional<Integer> discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public DisountRule createDisountRule() {
        return new DisountRule();
    }

}