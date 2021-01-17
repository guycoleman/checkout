package com.app;

import com.app.model.Basket;
import com.app.model.Product;
import com.app.promotion.PromotionRule;

import java.util.Collection;
import java.util.Collections;

public class Checkout {
    private final Basket basket = new Basket();
    private final PriceCalculator priceCalculator;

    public Checkout() {
        priceCalculator = new PromotionPriceCalculator(Collections.emptyList());
    }

    public Checkout(Collection<PromotionRule> promotionRules) {
        priceCalculator = new PromotionPriceCalculator(promotionRules);
    }

    public Checkout scan(Product product) {
        basket.addItem(product);
        basket.incrementSubTotal(product.getPrice());
        return this;
    }

    public Double total() {
        return priceCalculator.total(basket);
    }

    public Basket getBasket() {
        return basket;
    }
}
