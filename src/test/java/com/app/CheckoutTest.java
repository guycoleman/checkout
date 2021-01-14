package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    void setUp() {

    }

    @Test
    void emptyCheckoutShouldTotalZero() {
        checkout = new Checkout(Collections.emptyList());
        assertEquals(0.0, checkout.total());
    }

    @Test
    void emptyCheckoutShouldHaveEmptyItems() {
        checkout = new Checkout(Collections.emptyList());
        assertTrue(checkout.getBasket().getItems().isEmpty());
    }

    @Test
    void shouldScanItemWhichIsNotInCheckout() {
        checkout = new Checkout(Collections.emptyList());
        checkout.scan(TestData.product1());

        assertEquals(1, checkout.getBasket().getItems().size());
    }

    @Test
    void shouldReturnCorrectTotalWithNoRules() {
        checkout = new Checkout(Collections.emptyList());

        checkout.scan(TestData.product1());
        checkout.scan(TestData.product1());
        checkout.scan(TestData.product1());

        checkout.scan(TestData.product2());
        checkout.scan(TestData.product2());

        checkout.scan(TestData.product3());

        assertEquals(137.7, checkout.total());
    }

    @Test
    void shouldReturnCorrectTotalForExample1WithoutPromotions() {
        checkout = new Checkout(Collections.emptyList());

        checkout.scan(TestData.product1());
        checkout.scan(TestData.product2());
        checkout.scan(TestData.product3());

        assertEquals(74.2, checkout.total());
    }

    @Test
    void shouldReturnCorrectTotalForExample1WithPromotions() {
        checkout = new Checkout(TestData.allRules());

        checkout.scan(TestData.product1());
        checkout.scan(TestData.product2());
        checkout.scan(TestData.product3());

        assertEquals(66.78, checkout.total());
    }

    @Test
    void shouldReturnCorrectTotalForExample2WithPromotions() {
        checkout = new Checkout(TestData.allRules());

        checkout.scan(TestData.product1());
        checkout.scan(TestData.product3());
        checkout.scan(TestData.product1());

        assertEquals(36.95, checkout.total());
    }

    @Test
    void shouldReturnCorrectTotalForExample3WithoutPromotions() {
        checkout = new Checkout(Collections.emptyList());

        checkout.scan(TestData.product1());
        checkout.scan(TestData.product2());
        checkout.scan(TestData.product1());
        checkout.scan(TestData.product3());

        assertEquals(83.45, checkout.total());
    }

    @Test
    void shouldReturnCorrectTotalForExample3WithPromotions() {
        checkout = new Checkout(TestData.allRules());

        checkout.scan(TestData.product1());
        checkout.scan(TestData.product2());
        checkout.scan(TestData.product1());
        checkout.scan(TestData.product3());

        assertEquals(73.76, checkout.total());
    }
}