package com.app.promotion;

import com.app.TestData;
import com.app.model.Basket;
import com.app.model.Discount;
import com.app.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiBuyFixedDiscountPromotionRuleTest {
    @Test
    void shouldHaveDefaultConstructor() {
        new MultiBuyFixedDiscountPromotionRule();
    }

    @Test
    void shouldWorkIfProductAndQuantityMatch() {
        Product product1 = TestData.product1();
        PromotionRule rule = new MultiBuyFixedDiscountPromotionRule("desc", product1.getId(), 2, 5);

        Basket basket = new Basket()
                .addItem(product1)
                .addItem(product1);

        Discount discount = rule.calculateDiscount(basket);
        assertNotNull(discount);
        assertEquals("desc", discount.getDescription());
        assertEquals(8.5, discount.getAmount());
    }

    @Test
    void shouldNotWorkIfProductDoesNotMatch() {
        PromotionRule rule = new MultiBuyFixedDiscountPromotionRule("desc", TestData.product1().getId(), 2, 5);

        Basket basket = new Basket()
                .addItem(TestData.product2())
                .addItem(TestData.product2());

        Discount discount = rule.calculateDiscount(basket);
        assertNull(discount);
    }

    @Test
    void shouldNotWorkIfQuantityDoesNotMatch() {
        Product product1 = TestData.product1();
        PromotionRule rule = new MultiBuyFixedDiscountPromotionRule("desc", product1.getId(), 2, 5);

        Basket basket = new Basket()
                .addItem(product1);

        Discount discount = rule.calculateDiscount(basket);
        assertNull(discount);
    }
}