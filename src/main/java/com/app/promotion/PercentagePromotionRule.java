package com.app.promotion;

import com.app.model.Basket;
import com.app.model.Discount;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class PercentagePromotionRule implements PromotionRule {
    public static final String TYPE = "PercentagePromotionRule";

    private String description;
    private double minSpend;
    /**
     * Percentage value between 0 and 1.
     */
    private double discountPercentage;

    public PercentagePromotionRule() {
    }

    public String getDescription() {
        return description;
    }

    public double getMinSpend() {
        return minSpend;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public PercentagePromotionRule(String description, double minSpend, double discountPercentage) {
        this.description = description;
        this.minSpend = minSpend;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public Discount calculateDiscount(Basket basket) {
        /*
         * Check the minSpend condition on the total before any promotions have been applied.
         * It's more likely to be what the customer expects.
         */
        if (basket.getSubTotal() >= minSpend) {
            double discountAmount = basket.getTotal() * discountPercentage;
            return new Discount(description, discountAmount);
        }

        return null;
    }
}
