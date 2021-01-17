package com.app.model;

public class Discount {
    private String description;
    private double amount;

    public Discount(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
