package com.app.promotion;

import com.app.model.Basket;
import com.app.model.Discount;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PromotionRuleParser.PromotionRuleDeserialiser.class)
public interface PromotionRule {
    Discount calculateDiscount(Basket basket);
}
