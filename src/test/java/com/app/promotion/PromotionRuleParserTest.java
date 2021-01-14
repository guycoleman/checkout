package com.app.promotion;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromotionRuleParserTest {
    @Test
    void parseRulesShouldWork() throws Exception {
        InputStream is = getClass().getResourceAsStream("/promotion_rules.json");
        PromotionRuleParser parser = new PromotionRuleParser();
        List<PromotionRule> rules = parser.parseRules(is);
        assertNotNull(rules);
        assertEquals(2, rules.size());

        assertTrue(rules.get(0) instanceof MultiBuyFixedDiscountPromotionRule);
        MultiBuyFixedDiscountPromotionRule rule0 = (MultiBuyFixedDiscountPromotionRule) rules.get(0);
        assertEquals("001", rule0.getProductId());
        assertEquals(2, rule0.getMinQuantity());
        assertEquals(8.50, rule0.getDiscountedPrice());

        assertTrue(rules.get(1) instanceof PercentagePromotionRule);
        PercentagePromotionRule rule1 = (PercentagePromotionRule) rules.get(1);
        assertEquals(60, rule1.getMinSpend());
        assertEquals(0.1, rule1.getDiscountPercentage());
    }
}