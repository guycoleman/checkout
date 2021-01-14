package com.app;

import com.app.model.Product;
import com.app.promotion.MultiBuyFixedDiscountPromotionRule;
import com.app.promotion.PercentagePromotionRule;
import com.app.promotion.PromotionRule;

import java.util.Arrays;
import java.util.Collection;

public class TestData {
    public static Product product1() {
        return new Product("001", "Travel Card Holder", 9.25);
    }

    public static Product product2() {

        return new Product("002", "Personalised cufflinks", 45.00);
    }

    public static Product product3() {
        return new Product("003", "Kids T-shirt", 19.95);
    }

    public static PromotionRule over60Rule() {
        return new PercentagePromotionRule("Spend over £60 and get 10% off", 60.00, 0.1);
    }

    public static PromotionRule travelCardHolderRule() {
        String description = "Buy 2 or more travel card holders then the price drops to £8.50";
        return new MultiBuyFixedDiscountPromotionRule(description, product1().getId(), 2, 8.50);
    }

    public static Collection<PromotionRule> allRules() {
        return Arrays.asList(travelCardHolderRule(), over60Rule());
    }
}
