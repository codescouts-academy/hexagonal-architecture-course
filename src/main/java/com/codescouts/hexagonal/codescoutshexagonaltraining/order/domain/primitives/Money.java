package com.codescouts.hexagonal.codescoutshexagonaltraining.order.domain.primitives;

import com.codescouts.hexagonal.codescoutshexagonaltraining.common.domain.ValueObject;

public final class Money implements ValueObject<Money> {
    private static final float MAX_VALUE = 1_000_000.f;

    private final float money;
    private final Currency currency;

    private Money(float money, Currency currency) {
        this.money = money;
        this.currency = currency;
    }

    public static final Money of(float money, String currencyCode) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be less than zero.");
        }
        if (money > MAX_VALUE) {
            throw new IllegalArgumentException("Money cannot be greater than " + MAX_VALUE + ".");
        }

        return new Money(money, Currency.ofCode(currencyCode));
    }

    public Money add(Money summand) {
        if (this.currency != summand.currency) {
            throw new IllegalArgumentException("Cannot add money with different currencies.");
        }

        return new Money(this.money + summand.value(), this.currency);
    }

    public float value() {
        return money;
    }

    public Money multiply(Quantity quantity) {
        return new Money(this.money * quantity.value(), this.currency);
    }

    @Override
    public boolean sameValueAs(Money other) {
        return other != null && this.money == other.money && this.currency.sameValueAs(other.currency);
    }
}