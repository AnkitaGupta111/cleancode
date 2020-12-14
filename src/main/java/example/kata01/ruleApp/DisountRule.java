package example.kata01.ruleApp;

import lombok.Data;

import java.util.Optional;

@Data
public class DisountRule {
    int ruleId;
    Optional<Integer> orderQuantity;
    Optional<Integer> discountQuantity;
    Optional<Integer> discountPrice;
    Optional<Integer> discountPercentage;
}
