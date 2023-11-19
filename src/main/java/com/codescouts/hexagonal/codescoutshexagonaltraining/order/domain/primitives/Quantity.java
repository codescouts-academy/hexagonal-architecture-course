package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.ValueObject;

public final class Quantity implements ValueObject<Quantity> {
    private final float quantity;

    private Quantity(float quantity) {
        this.quantity = quantity;
    }

    public static final Quantity of(float quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than zero!");
        }

        return new Quantity(quantity);
    }

    public float value() {
        return this.quantity;
    }

    @Override
    public boolean sameValueAs(Quantity other) {
        return other != null && this.quantity == other.quantity;
    }

    public Quantity subtract(Quantity quantity) {
        return new Quantity(this.quantity - quantity.value());
    }

    public boolean isBiggerOrEqualThan(Quantity quantity) {
        return this.quantity >= quantity.quantity;
    }
}

