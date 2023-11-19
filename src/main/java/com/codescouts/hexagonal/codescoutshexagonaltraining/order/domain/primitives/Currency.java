package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.ValueObject;

public class Currency implements ValueObject<Currency> {
    private static final Currency USD = new Currency("USD");
    private static final Currency EUR = new Currency("EUR");
    public final String code;

    private Currency(String code) {
        this.code = code;
    }

    public static final Currency ofCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Currency code cannot be null or empty.");
        }

        if (code.equals("USD")) {
            return USD;
        }

        if (code.equals("EUR")) {
            return EUR;
        }

        throw new IllegalArgumentException("Currency code is not supported.");
    }

    @Override
    public boolean sameValueAs(Currency other) {
        return other != null && this.code.equals(other.code);
    }
}
