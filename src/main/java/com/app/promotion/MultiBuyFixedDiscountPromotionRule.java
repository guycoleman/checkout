package com.app.promotion;

import com.app.model.Basket;
import com.app.model.BasketItem;
import com.app.model.Discount;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class MultiBuyFixedDiscountPromotionRule implements PromotionRule {
    public static final String TYPE = "MultiBuyFixedDiscountPromotionRule";

    private String description;
    private String productId;
    private int minQuantity;
    private double discountedPrice;

    public MultiBuyFixedDiscountPromotionRule() {
    }

    public MultiBuyFixedDiscountPromotionRule(String description, String productId, int minQuantity, double discountedPrice) {
        this.description = description;
        this.productId = productId;
        this.minQuantity = minQuantity;
        this.discountedPrice = discountedPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getProductId() {
        return productId;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    @Override
    public Discount calculateDiscount(Basket basket) {
        BasketItem item = basket.getItem(productId);
        if (item != null) {
            if (item.getProduct().getId().equals(productId)) {
                if (item.getQuantity() >= minQuantity) {
                    double discountAmount = item.getProduct().getPrice() - discountedPrice;
                    return new Discount(description, discountAmount * item.getQuantity());
                }
            }
        }

        return null;
    }
}
