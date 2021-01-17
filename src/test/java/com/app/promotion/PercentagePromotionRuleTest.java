package com.app.promotion;

import com.app.Checkout;
import com.app.TestData;
import com.app.model.Basket;
import com.app.model.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentagePromotionRuleTest {

    private PromotionRule rule;

    @BeforeEach
    void setUp() {
        rule = new PercentagePromotionRule("desc", 60.00, 0.1);
    }

    @Test
    void shouldHaveDefaultConstructor() {
        new PercentagePromotionRule();
    }

    @Test
    void shouldNotTriggerWhenBelowMinTotal() {
        Basket basket = new Checkout()
                .scan(TestData.product2())
                .getBasket();

        Discount discount = rule.calculateDiscount(basket);
        assertNull(discount);
    }

    @Test
    void shouldTriggerWhenAboveMinTotal() {
        Basket basket = new Checkout()
                .scan(TestData.product2())
                .scan(TestData.product2())
                .getBasket();

        Discount discount = rule.calculateDiscount(basket);
        assertNotNull(discount);
        assertEquals("desc", discount.getDescription());
        assertEquals(9.00, discount.getAmount());
    }
}