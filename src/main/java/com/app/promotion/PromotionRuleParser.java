package com.app.promotion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PromotionRuleParser {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public List<PromotionRule> parseRules(InputStream rules) throws IOException {
        TypeReference<List<PromotionRule>> typeReference = new TypeReference<List<PromotionRule>>() {
        };
        return objectMapper.readValue(rules, typeReference);
    }

    public static class PromotionRuleDeserialiser extends StdDeserializer<PromotionRule> {
        protected PromotionRuleDeserialiser() {
            super(PromotionRule.class);
        }

        @Override
        public PromotionRule deserialize(JsonParser jp, DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);
            String type = node.get("type").asText();

            switch (type) {
                case MultiBuyFixedDiscountPromotionRule.TYPE:
                    return jp.getCodec().treeToValue(node, MultiBuyFixedDiscountPromotionRule.class);
                case PercentagePromotionRule.TYPE:
                    return jp.getCodec().treeToValue(node, PercentagePromotionRule.class);
                default:
                    throw new IllegalArgumentException("Unsupported rule type " + type);
            }
        }
    }
}
