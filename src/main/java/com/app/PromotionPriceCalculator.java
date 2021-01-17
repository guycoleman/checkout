package com.app;

import com.app.model.Basket;
import com.app.model.Discount;
import com.app.promotion.PromotionRule;

import java.util.Collection;

public class PromotionPriceCalculator implements PriceCalculator {
    private final Collection<PromotionRule> promotionRules;

    public PromotionPriceCalculator(Collection<PromotionRule> promotionRules) {
        this.promotionRules = promotionRules;
    }

    @Override
    public double total(Basket basket) {
        basket.setDiscountTotal(0);
        for (PromotionRule rule : promotionRules) {
            Discount discount = rule.calculateDiscount(basket);
            if (discount != null) {
                basket.incrementDiscountTotal(discount.getAmount());
            }
        }

        double total = basket.getTotal();
        if (total < 0) {
            throw new IllegalStateException();
        }
        total = roundToTwoDecimalPlaces(total);
        return total;
    }

    private double roundToTwoDecimalPlaces(double finalTotal) {
        finalTotal = (double) Math.round(finalTotal * 100) / 100;
        return finalTotal;
    }
}
