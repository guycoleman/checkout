package com.app.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {
    /**
     * LinkedHashMap to preserve insertion order
     */
    private final Map<String, BasketItem> items = new LinkedHashMap<>();
    /**
     * Total without any promotions
     */
    private double subTotal = 0;
    /**
     * Total of all discount promotions
     */
    private double discountTotal = 0;

    public Basket addItem(Product product) {
        BasketItem item = items.get(product.getId());
        if (item == null) {
            item = new BasketItem(product, 0);
            items.put(product.getId(), item);
        }
        item.setQuantity(item.getQuantity() + 1);
        return this;
    }

    public BasketItem getItem(String productId) {
        return items.get(productId);
    }

    public Collection<BasketItem> getItems() {
        return items.values();
    }

    public int getSize() {
        return items.size();
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void incrementSubTotal(double amount) {
        subTotal += amount;
    }

    public double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public void incrementDiscountTotal(double amount) {
        discountTotal += amount;
    }

    public double getTotal() {
        return subTotal - discountTotal;
    }
}
