package com.app;

import com.app.model.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionPriceCalculatorTest {

    private PromotionPriceCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PromotionPriceCalculator(TestData.allRules());
    }

    @Test
    void emptyItemsShouldReturnZero() {
        Basket basket = new Basket();
        assertEquals(0.0, calculator.total(basket));
    }

    @Test
    void shouldWork() {
        Basket basket = new Checkout()
                .scan(TestData.product1())
                .scan(TestData.product1())
                .scan(TestData.product2())
                .scan(TestData.product3())
                .getBasket();
        double total = calculator.total(basket);
        assertEquals(73.76, total);
    }
}