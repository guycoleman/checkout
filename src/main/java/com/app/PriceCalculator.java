package com.app;

import com.app.model.Basket;

public interface PriceCalculator {
    double total(Basket basket);
}
