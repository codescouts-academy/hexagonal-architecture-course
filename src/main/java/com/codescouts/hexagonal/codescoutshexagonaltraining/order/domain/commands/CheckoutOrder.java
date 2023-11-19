package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.commands;

import com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives.Currency;

import java.util.List;
import java.util.UUID;

public class CheckoutOrder {
    public final UUID customer;
    public final List<OrderProductLine> items;
    public final Currency currency;

    private CheckoutOrder(UUID customer, List<OrderProductLine> items, Currency currency) {
        this.customer = customer;
        this.items = items;
        this.currency = currency;
    }

    public static CheckoutOrder Create(UUID customer, List<OrderProductLine> items, String currencyCode) {
        return new CheckoutOrder(customer, items, Currency.ofCode(currencyCode));
    }
}
